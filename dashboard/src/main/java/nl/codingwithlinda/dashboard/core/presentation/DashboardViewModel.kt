package nl.codingwithlinda.dashboard.core.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.authentication.core.domain.usecase.LoggedInAccountUseCase
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.core_ui.date_time.DateTimeConverter
import nl.codingwithlinda.dashboard.categories.data.CategoryFactory
import nl.codingwithlinda.dashboard.core.domain.usecase.PreferencesForAccountUseCase
import nl.codingwithlinda.dashboard.core.domain.usecase.TestTransactionsUseCase
import nl.codingwithlinda.dashboard.core.domain.usecase.TransactionForAccountUseCase
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.core.presentation.state.DashboardCreateTransactionUiState
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.DayDiff
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDateGroup
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.toUi
import java.time.format.FormatStyle

class DashboardViewModel(
    private val currencyFormatterFactory: CurrencyFormatterFactory,
    private val categoryFactory: CategoryFactory,
    private val loggedInAccountUseCase: LoggedInAccountUseCase,
    private val transactionForAccountUseCase: TransactionForAccountUseCase,
    private val preferencesForAccountUseCase: PreferencesForAccountUseCase,
    private val testTransactionsUseCase: TestTransactionsUseCase,
): ViewModel() {

    private val account = loggedInAccountUseCase.loggedInAccount.map{res ->
        when(res){
            is SpendResult.Failure -> null
            is SpendResult.Success -> res.data
        }
    }
    private val _transactions = transactionForAccountUseCase.transactionsForLoggedInAccount()
        .onEach {

        }.map {res ->
            when(res){
                is SpendResult.Failure -> {
                    emptyList()
                }
                is SpendResult.Success -> {
                    res.data
                }
            }
        }

    private fun mostPopularCategory() = _transactions.mapNotNull { transactions ->
        val categories = transactions.map {
            it.category
        }

        val mostPopularCategory = categories.groupingBy {
            it
        }.eachCount().maxByOrNull {
            it.value
        }?.key

        mostPopularCategory?.let {
            try {
                categoryFactory.getCategoryUi(it)
            } catch (e: Exception) {
                return@mapNotNull null
            }
        }
    }


    private val prefs = preferencesForAccountUseCase.preferencesForLoggedInAccount().map { res ->
        when(res){
            is SpendResult.Failure ->{
                null
            }
            is SpendResult.Success -> {
                res.data
            }
        }
    }

    private val _accountUiState = MutableStateFlow(AccountUiState())

    val accountUiState = combine(
        account,
        prefs,
        _transactions,
        _accountUiState){ account, prefs, transactions, accountUiState ->

        if (account == null){
            return@combine accountUiState
        }
        if (prefs == null){
            return@combine accountUiState
        }

        val accountBalance = transactions.sumOf {
            it.amount
        }
        val accountBalanceUi = currencyFormatterFactory.getFormatter(accountBalance).formatCurrencyString(accountBalance, prefs.preferences)

        val largestTransaction =  transactions.maxByOrNull {
            it.amount.abs()
        }?.toUi(currencyFormatterFactory, prefs.preferences )

        accountUiState.copy(
            userName = account.userName ,
            accountBalance = accountBalanceUi.text,
            largestTransaction = largestTransaction,
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _accountUiState.value)


    @SuppressLint("NewApi")
    val transactions = _transactions
        .combine(prefs){ transactions, prefs ->

            if (prefs == null){
                return@combine emptyList()
            }
            transactions.groupByDateGroup()
                .filterNot {
                    it.date.isOlder()
                }
                .toUi(
                    currencyFormatterFactory = currencyFormatterFactory,
                    preferences = prefs.preferences,
                    formatter = DateTimeConverter.MEDIUM_DATE
                )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    private val _dashboardCreateTransactionUiState = MutableStateFlow(
        DashboardCreateTransactionUiState()
    )
    val dashboardCreateTransactionUiState = _dashboardCreateTransactionUiState.asStateFlow()

    fun onCreateTransaction() {
        _dashboardCreateTransactionUiState.update {
            it.copy(
                showCreateTransaction = !it.showCreateTransaction
            )
        }
    }
    init {
        viewModelScope.launch {
            //TESTING DELETE LATER
            runBlocking {
                testTransactionsUseCase.insertFakeTransactions()
            }
            //END TESTING DELETE LATER

            mostPopularCategory().collect{mostPopularCategory ->
                _accountUiState.update {
                    it.copy(
                        mostPopularCategory = mostPopularCategory
                    )
                }
            }
        }
    }
}