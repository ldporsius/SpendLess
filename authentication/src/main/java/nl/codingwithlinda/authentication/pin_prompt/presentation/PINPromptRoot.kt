package nl.codingwithlinda.authentication.pin_prompt.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.receiveAsFlow
import nl.codingwithlinda.authentication.core.data.AccountFactory
import nl.codingwithlinda.authentication.core.domain.usecase.LoggedInAccountUseCase
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.navigation.NavigationEvent
import nl.codingwithlinda.core.presentation.util.ObserveAsEvents

@Composable
fun PINPromptRoot(
    appModule: AppModule,
    onNavAction: (NavigationEvent) -> Unit
) {

    val sessionManager = appModule.sessionManager

    val loggedInAccountUseCase = LoggedInAccountUseCase(
        accountAccess = appModule.accountAccessReadOnly,
        sessionManager = sessionManager
    )

    val factory = viewModelFactory {
        initializer {
            PINPromptViewModel(
                authenticationManager = appModule.authenticationManager,
                onLoginSuccess = {
                    println("login success")
                    onNavAction(NavigationEvent.RedirectToDashboard)
                },
                loggedInAccountUseCase = loggedInAccountUseCase,
            )
        }
    }

    val viewModel: PINPromptViewModel = viewModel(
        factory = factory
    )

    ObserveAsEvents(sessionManager.isUserLoggedIn()) { loggedIn ->
        if (!loggedIn) {
            onNavAction(NavigationEvent.NavToLogin)
        }
    }

   PINPromptScreen(
       uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
       pinUiState = viewModel.pinUiState.collectAsStateWithLifecycle().value,
       onPINKeyboardAction = viewModel::handleAction,
       error = viewModel.errorChannel.collectAsStateWithLifecycle(null).value,
       onLogout = viewModel::logout
   )
}