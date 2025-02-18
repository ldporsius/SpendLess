package nl.codingwithlinda.dashboard.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.test_data.fakePreferences
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.categories.data.CategoryFactory
import nl.codingwithlinda.dashboard.categories.presentation.mapping.mapExpenseCategoryIdentifierToDomain
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.toUi

class DashboardViewModel(
    currencyFormatterFactory: CurrencyFormatterFactory,
    private val categoryFactory: CategoryFactory,
    appModule: AppModule
): ViewModel() {

    private val accountAccess = appModule.accountAccess
    private val preferencesAccess = appModule.preferencesAccess
    private val transactionsAccess = appModule.transactionsAccess

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
            println("Most popular category identifier: $it")
            it ?: return@mapNotNull null

            println("Most popular category ${mapExpenseCategoryIdentifierToDomain(it)}")
            categoryFactory.getCategoryUi(it)
        }catch (e: Exception){
            return@mapNotNull null
        }

    }

    private val _accountUiState = MutableStateFlow(AccountUiState())
    val accountUiState = combine(accountAccess.readAll(), _accountUiState){ accounts, accountUiState ->
        accountUiState.copy(
            userName = accounts.firstOrNull()?.userName ?: "",
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _accountUiState.value)

    private val _transactions = transactionsAccess.readAll()

    val transactions = _transactions.combine(preferencesAccess.readAll()) { transactions, preferences ->
        transactions.groupByDate().toUi(
            currencyFormatterFactory = currencyFormatterFactory,
            preferences = preferences.firstOrNull() ?: fakePreferences()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    init {
        viewModelScope.launch {
            transactionsAccess.delete(-1)
            fakeTransactions().onEach {
                transactionsAccess.create(it)
            }

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