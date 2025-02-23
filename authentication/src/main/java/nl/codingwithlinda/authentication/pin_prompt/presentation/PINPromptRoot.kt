package nl.codingwithlinda.authentication.pin_prompt.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.authentication.core.domain.usecase.LoggedInAccountUseCase
import nl.codingwithlinda.core.di.AppModule

@Composable
fun PINPromptRoot(
    appModule: AppModule,
    onNavAction: () -> Unit
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

                    onNavAction()
                },
                loggedInAccountUseCase = loggedInAccountUseCase,
            )
        }
    }

    val viewModel: PINPromptViewModel = viewModel(
        factory = factory
    )

   PINPromptScreen(
       uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
       pinUiState = viewModel.pinUiState.collectAsStateWithLifecycle().value,
       onPINKeyboardAction = viewModel::handleAction,
       error = viewModel.errorChannel.collectAsStateWithLifecycle(null).value,
       onLogout = viewModel::logout
   )
}