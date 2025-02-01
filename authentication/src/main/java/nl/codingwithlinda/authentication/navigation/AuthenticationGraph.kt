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
import nl.codingwithlinda.authentication.registration.create_pin.CreatePinViewModel
import nl.codingwithlinda.authentication.registration.create_pin.presentation.CreatePinHeader
import nl.codingwithlinda.authentication.core.presentation.components.CreatePinScreen
import nl.codingwithlinda.authentication.registration.repeat_pin.RepeatPinViewModel
import nl.codingwithlinda.authentication.registration.user_name.presentation.RegisterUserNameScreen
import nl.codingwithlinda.authentication.registration.user_name.presentation.RegisterUserViewModel
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
        val userName = it.arguments?.getString("userName") ?: ""
        val factory = viewModelFactory {
            initializer {
                CreatePinViewModel { pin ->
                    navController.navigate(
                        AuthenticationNavRoute.RepeatPinRoute(
                        userName, pin)
                    )
                }
            }
        }
        val viewModel = viewModel<CreatePinViewModel>(
            factory = factory
        )

       CreatePinScreen(
           pinUiState = viewModel.uiState.collectAsStateWithLifecycle().value ,
           header = {
               CreatePinHeader()
           },
           onAction = viewModel::handleAction,
           onNavigate = {
               navController.popBackStack()
           }
       )
    }

    composable<AuthenticationNavRoute.RepeatPinRoute> {
       val pin = it.arguments?.getString("pin")?: ""
        val userName = it.arguments?.getString("userName")?: ""

        val factory = viewModelFactory {
            initializer {
                RepeatPinViewModel(
                    userName = userName,
                    pin = pin
                )
            }
        }
        val viewModel = viewModel<RepeatPinViewModel>(
            factory = factory
        )
        CreatePinScreen(
            pinUiState = viewModel.uiState.collectAsStateWithLifecycle().value ,
            header = {
                CreatePinHeader()
            },
            onAction = viewModel::handleAction,
            onNavigate = {
                navController.popBackStack()
            }
        )
    }

    composable<AuthenticationNavRoute.OnboardingPreferencesRoute>(){
        Text("Onboarding")
    }

    composable<AuthenticationNavRoute.LoginRoute> {
        Text(text = "Login")
    }
}