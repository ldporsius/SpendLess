package nl.codingwithlinda.dashboard.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.test_data.fakePreferences
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.toUi

class DashboardViewModel(
    currencyFormatterFactory: CurrencyFormatterFactory,
    appModule: AppModule
): ViewModel() {

    private val accountAccess = appModule.accountAccess
    private val preferencesAccess = appModule.preferencesAccess
    private val transactionsAccess = appModule.transactionsAccess

    private val _accountUiState = MutableStateFlow(AccountUiState())
    val accountUiState = _accountUiState.asStateFlow()

    private val _transactions = transactionsAccess.readAll()

    val transactions = _transactions.combine(preferencesAccess.readAll()) { transactions, preferences ->
        transactions.groupByDate().toUi(
            currencyFormatterFactory = currencyFormatterFactory,
            preferences = preferences.firstOrNull() ?: fakePreferences()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            fakeTransactions().onEach {
                transactionsAccess.create(it)
            }
        }
    }
}