package nl.codingwithlinda.spendless.navigation.authentication

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import nl.codingwithlinda.authentication.core.presentation.components.CreatePinScreen
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.authentication.login.domain.usecase.StartSessionUseCase
import nl.codingwithlinda.core_ui.shared_components.ErrorBanner
import nl.codingwithlinda.authentication.login.presentation.LoginScreen
import nl.codingwithlinda.authentication.login.presentation.LoginViewModel
import nl.codingwithlinda.authentication.onboarding.domain.SaveAccountAndPreferencesUseCase
import nl.codingwithlinda.authentication.onboarding.presentation.OnboardingScreen
import nl.codingwithlinda.authentication.onboarding.presentation.OnboardingViewModel
import nl.codingwithlinda.authentication.registration.create_pin.presentation.CreatePinHeader
import nl.codingwithlinda.authentication.registration.create_pin.presentation.CreatePinViewModel
import nl.codingwithlinda.authentication.registration.repeat_pin.RepeatPinHeader
import nl.codingwithlinda.authentication.registration.repeat_pin.RepeatPinViewModel
import nl.codingwithlinda.authentication.registration.user_name.presentation.RegisterUserNameScreen
import nl.codingwithlinda.authentication.registration.user_name.presentation.RegisterUserViewModel
import nl.codingwithlinda.authentication.core.data.AccountFactory
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.authentication.validation.UserNameValidator
import nl.codingwithlinda.authentication.pin_prompt.presentation.PINPromptRoot
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.CustomNavType
import nl.codingwithlinda.core.navigation.DashboardNavRoute
import nl.codingwithlinda.core.navigation.NavigationEvent
import nl.codingwithlinda.spendless.navigation.util.navigateToEvent
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterExpense
import kotlin.reflect.typeOf

fun NavGraphBuilder.authenticationNavGraph(
    //navController: NavController,
    onNavAction: (NavigationEvent) -> Unit,
    appModule: AppModule
) {


   /* composable<AuthenticationNavRoute.AuthenticationRoot> {
        AuthenticationRoot(
            appModule = appModule,
            onNavAction = onNavAction
        )
    }*/

    composable<AuthenticationNavRoute.RegisterUserNameRoute> {
        val factory = viewModelFactory {
            initializer {
                RegisterUserViewModel(
                    userNameValidator = UserNameValidator(accountAccess = appModule.accountAccess)
                )
            }
        }
        val viewModel = viewModel<RegisterUserViewModel>(
            factory = factory
        )

        RegisterUserNameScreen(
            uistate = viewModel.uiState.collectAsStateWithLifecycle().value,
            onAction = viewModel::handleAction,
            onNavigate = {
                //navController.navigateToEvent(it)
                onNavAction(it)
            }
        )
    }
    composable<AuthenticationNavRoute.CreatePinRoute>(
        typeMap = mapOf(
            typeOf<Account>() to CustomNavType.AccountType
        )
    ) {
        val userName = it.toRoute<AuthenticationNavRoute.CreatePinRoute>().userName
        val factory = viewModelFactory {
            initializer {
                CreatePinViewModel { pin ->
                    /*navController.navigate(
                        AuthenticationNavRoute.RepeatPinRoute(
                        userName, pin)
                    )*/
                    onNavAction(NavigationEvent.NavToRepeatPin(userName, pin))
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
               onNavAction(NavigationEvent.NavUp)
           }
       )
    }

    composable<AuthenticationNavRoute.RepeatPinRoute> { navBackStackEntry ->
        val pin = navBackStackEntry.arguments?.getString("pin")?: ""
        val userName = navBackStackEntry.arguments?.getString("userName")?: ""

        val factory = viewModelFactory {
            initializer {
                RepeatPinViewModel(
                    accountFactory = AccountFactory(),
                    userName = userName,
                    pin = pin,
                    navToOnboarding = {
                        //navController.navigate(AuthenticationNavRoute.OnboardingPreferencesRoute(it))
                        onNavAction(NavigationEvent.NavToOnboarding(it))
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
                onNavAction(NavigationEvent.PopBackStack)
            }
        )
    }

    composable<AuthenticationNavRoute.OnboardingPreferencesRoute>(
        typeMap = mapOf(
            typeOf<Account>() to CustomNavType.AccountType
        )
    ){

        val args = it.toRoute<AuthenticationNavRoute.OnboardingPreferencesRoute>()

        val saveAccountAndPreferencesUseCase = SaveAccountAndPreferencesUseCase(
            accountFactory = AccountFactory(),
            accountAccess = appModule.accountAccess,
            preferencesAccess = appModule.preferencesAccess,
        )
        val currencyFormatter = CurrencyFormatterExpense(LocalContext.current)

        val factory = viewModelFactory {
            initializer {
                OnboardingViewModel(
                    currencyFormatter = currencyFormatter,
                    account = args.account,
                    saveAccountAndPreferencesUseCase = saveAccountAndPreferencesUseCase,
                    navToDashboard = {
                        onNavAction(NavigationEvent.NavToDashboard)
                       /* navHostController.navigate(DashboardNavRoute.DashboardRoot){
                            popUpTo(AuthenticationNavRoute.AuthenticationRoot){
                                inclusive = true
                            }
                        }*/
                    }
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
                onNavAction(it)
               /* if (it is NavigationEvent.RedirectToDashboard){
                    navHostController.navigate(DashboardNavRoute.DashboardRoot){
                        popUpTo(AuthenticationNavRoute.AuthenticationRoot){
                            inclusive = true
                        }
                    }
                }
                else {
                    navController.navigateToEvent(it)
                }*/
            }
        )
    }

    composable<AuthenticationNavRoute.PINPromptRoute> {
        PINPromptRoot(
            appModule = appModule,
            onNavAction = {
               // navController.navigateToEvent(it)
                onNavAction(it)

            }
        )
    }

    composable<AuthenticationNavRoute.LoginRoute> {
        val startSessionUseCase = StartSessionUseCase(appModule.sessionManager)

        val factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    loginValidator = LoginValidator(
                        accountAccess = appModule.accountAccess,
                        accountFactory = AccountFactory()
                    ),
                    startSessionUseCase = startSessionUseCase,
                    navToDashboard = {
                        //navHostController.navigate(DashboardNavRoute.DashboardRoot)
                        onNavAction(NavigationEvent.NavToDashboard)
                    }
                )
            }
        }
        val viewModel = viewModel<LoginViewModel>(
            factory = factory
        )
        LoginScreen(
            uistate = viewModel.uiState.collectAsStateWithLifecycle().value,
            error = viewModel.errorChannel.collectAsStateWithLifecycle(null).value,
            onAction = viewModel::handleAction,
            onNavigate = {
                println("LOGIN SCREEN NAVIGATE CALLED with event: $it")
                //navController.navigateToEvent(it)
                onNavAction(it)
            }
        )
    }
}