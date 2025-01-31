package nl.codingwithlinda.authentication.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nl.codingwithlinda.authentication.core.presentation.AuthenticationRootScreen
import nl.codingwithlinda.authentication.registration.presentation.RegisterUserNameScreen
import nl.codingwithlinda.authentication.registration.presentation.RegisterUserViewModel
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute

fun NavGraphBuilder.authenticationNavGraph() {
    composable<AuthenticationNavRoute.AuthenticationRoot> {
        AuthenticationRootScreen()
    }
    composable<AuthenticationNavRoute.RegisterUserNameRoute> {
        val viewModel = viewModel<RegisterUserViewModel>()

        RegisterUserNameScreen(
            uistate = viewModel.uiState.collectAsStateWithLifecycle().value,
            onAction = viewModel::handleAction
        )
    }

}