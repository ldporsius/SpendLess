package nl.codingwithlinda.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.pin_prompt.presentation.PINPromptRoot
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.dashboard.core.presentation.DashboardRoot
import nl.codingwithlinda.dashboard.settings.main.presentation.UserSettingsRoot
import nl.codingwithlinda.dashboard.settings.main.presentation.state.SettingsAction
import nl.codingwithlinda.dashboard.transactions.transactions_all.presentation.AllTransactionsRoot
import nl.codingwithlinda.spendless.navigation.authentication.AuthenticationNavRoute
import nl.codingwithlinda.spendless.navigation.authentication.PINPromptRoute
import nl.codingwithlinda.spendless.navigation.authentication.authenticationNavGraph
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute
import nl.codingwithlinda.spendless.navigation.dashboard.DashboardNavRoute
import nl.codingwithlinda.spendless.navigation.user_settings.UserSettingsGraph
import nl.codingwithlinda.spendless.navigation.user_settings.UserSettingsPreferencesNav
import nl.codingwithlinda.spendless.navigation.user_settings.UserSettingsRootNav
import nl.codingwithlinda.spendless.navigation.user_settings.UserSettingsSecurityNav
import nl.codingwithlinda.spendless.navigation.util.navigate
import nl.codingwithlinda.spendless.navigation.util.navigateToEvent

@Composable
fun SpendLessApp(
    appModule: AppModule,
    navHostController: NavHostController,
    onNavAction: (NavRoute) -> Unit
) {

    NavHost(navController = navHostController, startDestination = Destination.HomeGraph) {
        navigation<Destination.AuthenticationGraph>(startDestination = AuthenticationNavRoute.RegisterUserNameRoute){
            authenticationNavGraph(
                appModule = appModule,
                onNavAction = {
                    navHostController.navigateToEvent(it)
                }
            )

            composable<PINPromptRoute> {
                PINPromptRoot(
                    appModule = appModule,
                    onNavAction = {
                        //onNavAction(NavigationEvent.NavToDashboard)
                        navHostController.navigate(DashboardNavRoute.DashboardRoot){
                            popUpTo(PINPromptRoute){
                                inclusive = true
                            }
                        }
                    }
                )
            }

        }


        navigation<Destination.HomeGraph>(startDestination = DashboardNavRoute.DashboardRoot) {
            composable<DashboardNavRoute.DashboardRoot> {

                DashboardRoot(
                    appModule = appModule,
                    onShowAll = {
                        onNavAction(DashboardNavRoute.AllTransactionsNavRoute)
                    },
                    onNavToSettings = {
                        onNavAction(UserSettingsGraph)
                    }
                )
            }

            composable<DashboardNavRoute.AllTransactionsNavRoute> {
                AllTransactionsRoot(appModule)
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

            }
            composable<UserSettingsSecurityNav> {

            }
        }
    }
}

