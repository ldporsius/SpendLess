package nl.codingwithlinda.dashboard.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState

class DashboardViewModel(
    appModule: AppModule
): ViewModel() {


    private val accountAccess = appModule.accountAccess
    private val preferencesAccess = appModule.preferencesAccess

    private val _accountUiState = MutableStateFlow(AccountUiState())
    val accountUiState = _accountUiState.asStateFlow()

}