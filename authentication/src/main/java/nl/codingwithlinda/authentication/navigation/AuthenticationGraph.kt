package nl.codingwithlinda.authentication.navigation

import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import nl.codingwithlinda.authentication.core.presentation.AuthenticationRootScreen
import nl.codingwithlinda.authentication.core.presentation.components.CreatePinScreen
import nl.codingwithlinda.authentication.core.presentation.components.ErrorBanner
import nl.codingwithlinda.authentication.onboarding.OnboardingScreen
import nl.codingwithlinda.authentication.onboarding.OnboardingViewModel
import nl.codingwithlinda.authentication.registration.create_pin.CreatePinViewModel
import nl.codingwithlinda.authentication.registration.create_pin.presentation.CreatePinHeader
import nl.codingwithlinda.authentication.registration.repeat_pin.RepeatPinHeader
import nl.codingwithlinda.authentication.registration.repeat_pin.RepeatPinViewModel
import nl.codingwithlinda.authentication.registration.user_name.presentation.RegisterUserNameScreen
import nl.codingwithlinda.authentication.registration.user_name.presentation.RegisterUserViewModel
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.CustomNavType
import nl.codingwithlinda.core.navigation.navigateToEvent
import kotlin.reflect.typeOf

fun NavGraphBuilder.authenticationNavGraph(
    navController: NavController,
    appModule: AppModule
) {

    composable<AuthenticationNavRoute.AuthenticationRoot> {
        AuthenticationRootScreen(appModule)
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
           errorBanner = {},
           onAction = viewModel::handleAction,
           onNavigate = {
               navController.popBackStack()
           }
       )
    }

    composable<AuthenticationNavRoute.RepeatPinRoute> { navBackStackEntry ->
        val pin = navBackStackEntry.arguments?.getString("pin")?: ""
        val userName = navBackStackEntry.arguments?.getString("userName")?: ""

        val factory = viewModelFactory {
            initializer {
                RepeatPinViewModel(
                    accountFactory = nl.codingwithlinda.core.data.AccountFactory(),
                    userName = userName,
                    pin = pin,
                    navToOnboarding = {
                        navController.navigate(AuthenticationNavRoute.OnboardingPreferencesRoute(it))
                    }
                )
            }
        }
        val viewModel = viewModel<RepeatPinViewModel>(
            factory = factory
        )
        CreatePinScreen(
            pinUiState = viewModel.uiState.collectAsStateWithLifecycle().value ,
            header = {
                RepeatPinHeader()
            },
            errorBanner = {

                viewModel.errorChannel.collectAsStateWithLifecycle(null).value?.let { error ->
                    ErrorBanner(
                        modifier = it,
                        error = error)
                }
            },
            onAction = viewModel::handleAction,
            onNavigate = {
                navController.popBackStack()
            }
        )
    }

    composable<AuthenticationNavRoute.OnboardingPreferencesRoute>(
        typeMap = mapOf(
            typeOf<Account>() to CustomNavType.AccountType
        )
    ){


        val args = it.toRoute<AuthenticationNavRoute.OnboardingPreferencesRoute>()

        val factory = viewModelFactory {
            initializer {
                OnboardingViewModel(
                    currencyFormatter = appModule.currencyFormatter
                )
            }
        }
        val viewModel = viewModel<OnboardingViewModel>(
            factory = factory
        )

        OnboardingScreen(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            onAction = viewModel::handleAction,
            onNavigate = {
                navController.navigate(AuthenticationNavRoute.CreatePinRoute(args.account.userName))
            }
        )
    }

    composable<AuthenticationNavRoute.LoginRoute> {
        Text(text = "Login")
    }
}