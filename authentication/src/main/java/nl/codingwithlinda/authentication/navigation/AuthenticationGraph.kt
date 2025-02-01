package nl.codingwithlinda.authentication.navigation

import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nl.codingwithlinda.authentication.core.presentation.AuthenticationRootScreen
import nl.codingwithlinda.authentication.create_pin.CreatePinViewModel
import nl.codingwithlinda.authentication.create_pin.presentation.CreatePinScreen
import nl.codingwithlinda.authentication.registration.presentation.RegisterUserNameScreen
import nl.codingwithlinda.authentication.registration.presentation.RegisterUserViewModel
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.navigateToEvent

fun NavGraphBuilder.authenticationNavGraph(
    navController: NavController
) {

    composable<AuthenticationNavRoute.AuthenticationRoot> {
        AuthenticationRootScreen()
    }
    composable<AuthenticationNavRoute.RegisterUserNameRoute> {
        val viewModel = viewModel<RegisterUserViewModel>()

        RegisterUserNameScreen(
            uistate = viewModel.uiState.collectAsStateWithLifecycle().value,
            onAction = viewModel::handleAction,
            onNavigate = {
                navController.navigateToEvent(it)
            }
        )
    }
    composable<AuthenticationNavRoute.CreatePinRoute> {

        val factory = viewModelFactory {
            initializer {
                CreatePinViewModel { pin ->
                    navController.navigate(AuthenticationNavRoute.RepeatPinRoute(pin))
                }
            }
        }
        val viewModel = viewModel<CreatePinViewModel>(
            factory = factory
        )

       CreatePinScreen(
           pinUiState = viewModel.uiState.collectAsStateWithLifecycle().value ,
           onAction = viewModel::handleAction,
           onNavigate = {
               navController.popBackStack()
           }
       )
    }

    composable<AuthenticationNavRoute.RepeatPinRoute> {
        Text(text = "Repeat Pin")
    }

    composable<AuthenticationNavRoute.LoginRoute> {
        Text(text = "Login")
    }
}