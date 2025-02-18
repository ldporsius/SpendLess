package nl.codingwithlinda.dashboard.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.test_data.fakePreferences
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.categories.data.CategoryFactory
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.DayDiff
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.toUi

class DashboardViewModel(
    private val currencyFormatterFactory: CurrencyFormatterFactory,
    private val categoryFactory: CategoryFactory,
    appModule: AppModule
): ViewModel() {

    private val accountAccess = appModule.accountAccess
    private val preferencesAccess = appModule.preferencesAccess
    private val transactionsAccess = appModule.transactionsAccess
    private val _transactions = transactionsAccess.readAll()

    private suspend fun preferencesFirstOrNull() = preferencesAccess.readAll().firstOrNull()?.firstOrNull()

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

    private fun largestTransaction() = _transactions.combine(preferencesAccess.readAll()){ transactions, preferences ->
        transactions.maxByOrNull {
            it.amount.abs()
        }?.toUi(currencyFormatterFactory, preferencesFirstOrNull() ?: fakePreferences())
    }
    private val _accountUiState = MutableStateFlow(AccountUiState())

    val accountUiState = combine(
        preferencesAccess.readAll(),
        accountAccess.readAll(),
        largestTransaction(),

        _accountUiState){ prefs, accounts, largestTransaction, accountUiState ->
        accountUiState.copy(
            userName = accounts.firstOrNull()?.userName ?: "",
            largestTransaction = largestTransaction,
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _accountUiState.value)


    val transactions = _transactions.combine(preferencesAccess.readAll()) { transactions, preferences ->
        transactions.groupByDate()
            .filter {
                it.date != DayDiff.OLDER
            }
            .toUi(
            currencyFormatterFactory = currencyFormatterFactory,
            preferences = preferences.firstOrNull() ?: fakePreferences()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    init {
        viewModelScope.launch {
            //TESTING DELETE LATER
            transactionsAccess.delete(-1)
            runBlocking {
                fakeTransactions().onEach {
                    transactionsAccess.create(it)
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