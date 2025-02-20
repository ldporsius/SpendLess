package nl.codingwithlinda.authentication.welcome_back.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.navigation.NavigationEvent
import nl.codingwithlinda.core.presentation.util.ObserveAsEvents

@Composable
fun PINPromptRoot(
    appModule: AppModule,
    onNavAction: (NavigationEvent) -> Unit
) {

    val sessionManager = appModule.sessionManager
    val factory = viewModelFactory {
        initializer {
            PINPromptViewModel(
                sessionManager = sessionManager,
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
       onPINKeyboardAction = viewModel::handleAction,
       userName = "rockefeller74",
       onLogout = viewModel::logout
   )
}