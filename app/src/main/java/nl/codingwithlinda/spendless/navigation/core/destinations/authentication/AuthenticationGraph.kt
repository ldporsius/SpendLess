package nl.codingwithlinda.spendless.navigation.core.destinations.authentication

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.registration.common.CreatePinScreen
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.authentication.login.domain.usecase.StartSessionUseCase
import nl.codingwithlinda.core_ui.shared_components.ErrorBanner
import nl.codingwithlinda.authentication.login.presentation.LoginScreen
import nl.codingwithlinda.authentication.login.presentation.LoginViewModel
import nl.codingwithlinda.user_settings.onboarding.domain.SaveAccountAndPreferencesUseCase
import nl.codingwithlinda.user_settings.onboarding.presentation.OnboardingScreen
import nl.codingwithlinda.user_settings.preferences.presentation.UserPreferencesViewModel
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
import nl.codingwithlinda.core.data.encryption.AccountCryptor
import nl.codingwithlinda.core_ui.currency.AppCurrencySymbolProvider
import nl.codingwithlinda.spendless.navigation.util.custom_navtypes.AccountNavType
import nl.codingwithlinda.spendless.navigation.util.NavigationEvent
import nl.codingwithlinda.core_ui.currency.formatters.CurrencyFormatterExpense
import nl.codingwithlinda.spendless.navigation.core.destinations.AuthenticationNavRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.LoginRoute
import nl.codingwithlinda.user_settings.onboarding.domain.GetExampleUserPrefsUseCase
import kotlin.reflect.typeOf

fun NavGraphBuilder.authenticationNavGraph(
    onNavAction: (NavigationEvent) -> Unit,
    appModule: AppModule
) {


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
            navToCreatePin = {
                onNavAction(NavigationEvent.NavToCreatePin(viewModel.uiState.value.userNameInput))
            }
        )
    }
    composable<AuthenticationNavRoute.CreatePinRoute>(
        typeMap = mapOf(
            typeOf<Account>() to AccountNavType.AccountType
        )
    ) {
        val userName = it.toRoute<AuthenticationNavRoute.CreatePinRoute>().userName
        val factory = viewModelFactory {
            initializer {
                CreatePinViewModel { pin ->
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
            typeOf<Account>() to AccountNavType.AccountType
        )
    ){ backStackEntry ->

        val scope = rememberCoroutineScope()
        val args = backStackEntry.toRoute<AuthenticationNavRoute.OnboardingPreferencesRoute>()

        val saveAccountAndPreferencesUseCase = SaveAccountAndPreferencesUseCase(
            accountCryptor = AccountCryptor(),
            accountAccess = appModule.accountAccess,
            preferencesAccess = appModule.preferencesAccess,
            sessionManager = appModule.sessionManager
        )
        val currencySymbolProvider = AppCurrencySymbolProvider(LocalContext.current)
        val currencyFormatter = CurrencyFormatterExpense(currencySymbolProvider)

        val factory = viewModelFactory {
            initializer {
                UserPreferencesViewModel(
                    currencyFormatter = currencyFormatter,
                    getUserPreferencesUseCase = GetExampleUserPrefsUseCase(),
                    actionOnSave = {
                        scope.launch {
                            saveAccountAndPreferencesUseCase.save(args.account, it)
                            onNavAction(NavigationEvent.NavToDashboard)
                        }
                    }
                )
            }
        }
        val viewModel = viewModel<UserPreferencesViewModel>(
            factory = factory
        )

        OnboardingScreen(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            onAction = viewModel::handleAction,
            onNavigate = {
                onNavAction(NavigationEvent.NavToCreatePin(args.account.userName))
            }
        )
    }


    composable<LoginRoute> {
        val startSessionUseCase = StartSessionUseCase(appModule.sessionManager)

        val factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    loginValidator = LoginValidator(
                        accountAccess = appModule.accountAccess,
                        accountCryptor = AccountCryptor()
                    ),
                    startSessionUseCase = startSessionUseCase,
                    navToDashboard = {
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
                onNavAction(NavigationEvent.NavToRegisterUserName)
            }
        )
    }
}