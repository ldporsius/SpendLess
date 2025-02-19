package nl.codingwithlinda.dashboard.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.core.test_data.fakePreferencesAccount
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.categories.data.CategoryFactory
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.DayDiff
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.toUi

class DashboardViewModel(
    private val currencyFormatterFactory: CurrencyFormatterFactory,
    private val categoryFactory: CategoryFactory,
    private val sessionManager: SessionManager,
    appModule: AppModule
): ViewModel() {

    private val accountAccess = appModule.accountAccessReadOnly
    private val preferencesAccess = appModule.preferencesAccessForAccount
    private val transactionsAccess = appModule.transactionsAccess

    @OptIn(ExperimentalCoroutinesApi::class)
    private val account = sessionManager.getUserId()
        .flatMapConcat{userId ->
            userId?.let {
                accountAccess.read(userId)
            } ?: emptyFlow()
        }


    @OptIn(ExperimentalCoroutinesApi::class)
    private val _transactions = sessionManager.getUserId()
        .flatMapConcat{userId ->
            userId?.let {
                transactionsAccess.readAllFK(it)
            } ?: emptyFlow()
        }


    private fun mostPopularCategory() = _transactions.mapNotNull {
        it.map {
            it.category
        }
    }.map { list ->
        println("Categories: $list")
        list.groupingBy {
            it
        }.eachCount().maxByOrNull{
            it.value
        }?.key
    }.mapNotNull {
        try {
            it ?: return@mapNotNull null
            categoryFactory.getCategoryUi(it)
        }catch (e: Exception){
            return@mapNotNull null
        }
    }


    private val _accountUiState = MutableStateFlow(AccountUiState())

    val accountUiState = combine(
        account,
        _transactions,
        _accountUiState){ account, transactions, accountUiState ->

        if (account == null){
            return@combine accountUiState
        }
        val prefs = preferencesAccess.getById(account.id) ?: fakePreferencesAccount(account.id)

        val accountBalance = transactions.sumOf {
            it.amount
        }
        val accountBalanceUi = currencyFormatterFactory.getFormatter(accountBalance).formatCurrencyString(accountBalance, prefs.preferences)

        val largestTransaction =  transactions.maxByOrNull {
            it.amount.abs()
        }?.toUi(currencyFormatterFactory, prefs.preferences )

        accountUiState.copy(
            userName = account.userName ,
            accountBalance = accountBalanceUi,
            largestTransaction = largestTransaction,
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _accountUiState.value)


    val transactions = _transactions.map { transactions,->
        val accountId = transactions.firstOrNull()?.accountId ?: return@map emptyList<TransactionGroupUi>()
        val preferences = preferencesAccess.getById(accountId) ?: fakePreferencesAccount(accountId)
        transactions.groupByDate()
            .filter {
                it.date != DayDiff.OLDER
            }
            .toUi(
                currencyFormatterFactory = currencyFormatterFactory,
                preferences = preferences.preferences
            )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    init {
        viewModelScope.launch {
            //TESTING DELETE LATER
            transactionsAccess.delete(-1)

            runBlocking {
                account.firstOrNull()?.let {
                    fakeTransactions(it.id).onEach {
                        transactionsAccess.create(it)
                    }
                }

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