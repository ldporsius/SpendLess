package nl.codingwithlinda.spendless.navigation

import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.pin_prompt.presentation.PINPromptRoot
import nl.codingwithlinda.core.data.dto.TransactionDto
import nl.codingwithlinda.core.data.dto.toDomain
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.session_manager.ESessionState
import nl.codingwithlinda.core.presentation.util.ObserveAsEvents
import nl.codingwithlinda.core_ui.currency.AppCurrencySymbolProvider
import nl.codingwithlinda.core_ui.currency.formatters.CurrencyFormatterExpense
import nl.codingwithlinda.dashboard.core.presentation.DashboardRoot
import nl.codingwithlinda.dashboard.transactions.transactions_all.presentation.AllTransactionsRoot
import nl.codingwithlinda.spendless.navigation.core.destinations.AuthenticationNavRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.PINPromptRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.authentication.authenticationNavGraph
import nl.codingwithlinda.spendless.navigation.core.destinations.DashboardNavRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.UserSettingsGraph
import nl.codingwithlinda.spendless.navigation.core.destinations.UserSettingsPreferencesNav
import nl.codingwithlinda.spendless.navigation.core.destinations.UserSettingsRootNav
import nl.codingwithlinda.spendless.navigation.core.destinations.UserSettingsSecurityNav
import nl.codingwithlinda.spendless.navigation.util.custom_navtypes.NavRouteNavType
import nl.codingwithlinda.spendless.navigation.util.custom_navtypes.TransactionDtoNavType
import nl.codingwithlinda.spendless.navigation.util.navigateToEvent
import nl.codingwithlinda.user_settings.main.presentation.UserSettingsRoot
import nl.codingwithlinda.user_settings.main.presentation.state.SettingsAction
import nl.codingwithlinda.user_settings.preferences.domain.GetUserPreferencesUseCase
import nl.codingwithlinda.user_settings.preferences.domain.SaveUserPreferencesUseCase
import nl.codingwithlinda.user_settings.preferences.presentation.UserPreferencesScreen
import nl.codingwithlinda.user_settings.preferences.presentation.UserPreferencesViewModel
import nl.codingwithlinda.user_settings.security.domain.usecase.GetSecuritySettingsUseCase
import nl.codingwithlinda.user_settings.security.domain.usecase.SaveSecuritySettingsUseCase
import nl.codingwithlinda.user_settings.security.presentation.SecuritySettingsViewModel
import nl.codingwithlinda.user_settings.security.presentation.UserSettingsSecurityScreen
import kotlin.reflect.typeOf

@Composable
fun SpendLessApp(
    appModule: AppModule,
    navHostController: NavHostController,
    onNavAction: (NavRoute) -> Unit
) {

  /*  var showPINPrompt by remember { mutableStateOf(false) }
    val sessionEventFlow = Channel<ESessionState>()
    ObserveAsEvents(sessionEventFlow.receiveAsFlow()) {sessionState ->
        when(sessionState){
            ESessionState.OK -> {}
            ESessionState.LOGGED_OUT -> {}
            ESessionState.SESSION_EXPRIRED -> {
                showPINPrompt = true
            }
        }
    }
    if (showPINPrompt) {
        println("SHOWING PIN PROMPT triggered by event flow")
        Dialog(
            onDismissRequest = {  },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            PINPromptRoot(appModule) {
                showPINPrompt = false
            }
        }
    }*/
    NavHost(navController = navHostController, startDestination = Destination.HomeGraph) {
        navigation<Destination.AuthenticationGraph>(startDestination = AuthenticationNavRoute.RegisterUserNameRoute){
            authenticationNavGraph(
                appModule = appModule,
                onNavAction = {
                    navHostController.navigateToEvent(it)
                }
            )

            composable<PINPromptRoute>(
                        typeMap = mapOf(
                        typeOf<NavRoute>() to NavRouteNavType.NavRouteType
                        )
            ) {

                val args = it.toRoute<PINPromptRoute>().originalDestination
                println("PIN PROMPT ROUTE ORIGINAL DESTINATION: $args")
                PINPromptRoot(
                    appModule = appModule,
                    onNavAction = {
                        onNavAction(args)

                    }
                )
            }

        }


        navigation<Destination.HomeGraph>(startDestination = DashboardNavRoute.DashboardRoot) {
            composable<DashboardNavRoute.DashboardRoot> {

                val scope = rememberCoroutineScope()
                val context = LocalContext.current
                DashboardRoot(
                    appModule = appModule,
                    onShowAll = {
                        onNavAction(DashboardNavRoute.AllTransactionsNavRoute)
                    },
                    onNavToSettings = {
                        onNavAction(UserSettingsGraph)
                    },
                    onNavAction = {transaction->
                        println("DASHBOARD ROOT NAV ACTION CALLED with transaction: $transaction")
                        transaction?.let {
                            onNavAction(DashboardNavRoute.CreateTransactionNavRoute(it)
                            )
                        }
                        if (transaction == null) {
                            scope.launch {
                                Toast.makeText(context, "NO TRANSACTION", Toast.LENGTH_SHORT).show()
                            }
                            onNavAction(DashboardNavRoute.DashboardRoot)
                        }
                    }
                )
            }

            composable<DashboardNavRoute.AllTransactionsNavRoute> {
                AllTransactionsRoot(
                    appModule = appModule,
                    onNavBack = {
                        navHostController.navigateUp()
                    }
                )
            }

            composable<DashboardNavRoute.CreateTransactionNavRoute>(
                typeMap = mapOf(
                    typeOf<TransactionDto>() to TransactionDtoNavType.TransactionDtoType
                )
            ) { backStackEntry ->
                val transactionDto = backStackEntry.toRoute<DashboardNavRoute.CreateTransactionNavRoute>().transaction
                val saveTransactionUseCase = nl.codingwithlinda.dashboard.transactions.transaction_create.domain.usecase.SaveTransactionUseCase(
                    appModule.transactionsAccess
                )
                //val scope = rememberCoroutineScope()
                val context = LocalContext.current

                LaunchedEffect(transactionDto) {
                    launch {
                        saveTransactionUseCase.save(transactionDto.toDomain())
                        Toast.makeText(context, "TRANSACTION SAVED SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                    }
                }

                DashboardRoot(
                    appModule = appModule,
                    onShowAll = {
                        onNavAction(DashboardNavRoute.AllTransactionsNavRoute)
                    },
                    onNavToSettings = {
                        onNavAction(UserSettingsGraph)
                    },
                    onNavAction = {transaction->
                        transaction?.let {
                            onNavAction(DashboardNavRoute.CreateTransactionNavRoute(it)
                            )
                        }
                    }
                )
            }
        }

        navigation<UserSettingsGraph>(startDestination = UserSettingsRootNav) {
            composable<UserSettingsRootNav> {

                val scope = rememberCoroutineScope()
                UserSettingsRoot(
                    onNavBack = {
                        navHostController.navigateUp()
                    },
                    onAction = {action ->
                       when(action){
                           SettingsAction.NavigateToLogout -> {
                               scope.launch {
                                   appModule.authenticationManager.logout()
                                   onNavAction(Destination.HomeGraph)
                               }
                           }
                           SettingsAction.NavigateToPreferences -> {
                               navHostController.navigate(UserSettingsPreferencesNav)
                           }
                           SettingsAction.NavigateToSecurity -> {
                               navHostController.navigate(UserSettingsSecurityNav)
                           }
                       }
                    }
                )
            }
            composable<UserSettingsPreferencesNav> {
                val currencySymbolProvider = AppCurrencySymbolProvider(LocalContext.current)
                val currencyFormatter = CurrencyFormatterExpense(currencySymbolProvider)

                val scope = rememberCoroutineScope()

                val saveUserPreferencesUseCase = SaveUserPreferencesUseCase(
                    sessionManager = appModule.sessionManager,
                    preferencesAccess = appModule.preferencesAccess
                )
                val getPreferencesUseCase = GetUserPreferencesUseCase(
                    sessionManager = appModule.sessionManager,
                    preferencesAccess = appModule.preferencesAccessForAccount
                )

                val factory = viewModelFactory {
                    initializer {
                        UserPreferencesViewModel(
                            currencyFormatter = currencyFormatter,
                            getUserPreferencesUseCase = getPreferencesUseCase,
                            actionOnSave = { prefs ->
                                scope.launch {
                                    saveUserPreferencesUseCase.save(prefs)
                                    onNavAction(UserSettingsRootNav)
                                }
                            }
                        )
                    }
                }
                val viewModel = viewModel<UserPreferencesViewModel>(
                    factory = factory
                )

                UserPreferencesScreen(
                    uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                    onAction = viewModel::handleAction
                ) {
                    onNavAction(UserSettingsRootNav)
                }
            }


            composable<UserSettingsSecurityNav> {

                val getSecuritySettingsUseCase = GetSecuritySettingsUseCase(appModule.sessionManager)
                val saveSecuritySettingsUseCase = SaveSecuritySettingsUseCase(appModule.sessionManager)

                val factory = viewModelFactory {
                    initializer {
                        SecuritySettingsViewModel(
                            getSecuritySettingsUseCase = getSecuritySettingsUseCase,
                            saveSecuritySettingsUseCase = saveSecuritySettingsUseCase,
                            onSaved = {
                                println("SAVED SECURITY SETTINGS. NAVIGATING BACK TO SETTINGS ROOT")
                                onNavAction(UserSettingsRootNav)
                            }
                        )
                    }
                }
                val viewModel = viewModel<SecuritySettingsViewModel>(
                    factory = factory
                )

                UserSettingsSecurityScreen(
                    uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                    onAction = viewModel::handleAction,
                    onNavBack = {
                        onNavAction(UserSettingsRootNav)
                    }
                )
            }
        }
    }
}

