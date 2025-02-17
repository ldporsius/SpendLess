package nl.codingwithlinda.dashboard.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionUi

class DashboardViewModel(
    appModule: AppModule
): ViewModel() {


    private val accountAccess = appModule.accountAccess
    private val preferencesAccess = appModule.preferencesAccess

    private val _accountUiState = MutableStateFlow(AccountUiState())
    val accountUiState = _accountUiState.asStateFlow()
    private val _transactions = MutableStateFlow(emptyList<TransactionUi>())
    val transactions = _transactions.asStateFlow()

}