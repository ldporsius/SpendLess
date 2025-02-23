package nl.codingwithlinda.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import nl.codingwithlinda.authentication.pin_prompt.presentation.PINPromptRoot
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.spendless.navigation.dashboard.DashboardNavRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute
import nl.codingwithlinda.dashboard.core.presentation.DashboardRoot
import nl.codingwithlinda.dashboard.transactions.transactions_all.presentation.AllTransactionsRoot
import nl.codingwithlinda.spendless.navigation.authentication.AuthenticationNavRoute
import nl.codingwithlinda.spendless.navigation.authentication.PINPromptRoute
import nl.codingwithlinda.spendless.navigation.authentication.authenticationNavGraph
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination
import nl.codingwithlinda.spendless.navigation.util.NavigationEvent
import nl.codingwithlinda.spendless.navigation.util.navigateToEvent

@Composable
fun SpendLessApp(
    appModule: AppModule,
    navHostController: NavHostController,
    onNavAction: (NavRoute) -> Unit
) {

    NavHost(navController = navHostController, startDestination = Destination.HomeGraph) {

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

        navigation<Destination.AuthenticationGraph>(startDestination = AuthenticationNavRoute.RegisterUserNameRoute){
            authenticationNavGraph(
                appModule = appModule,
                onNavAction = {
                    navHostController.navigateToEvent(it)
                }
            )
        }


        navigation<Destination.HomeGraph>(startDestination = DashboardNavRoute.DashboardRoot) {
            composable<DashboardNavRoute.DashboardRoot> {

                DashboardRoot(
                    appModule = appModule,
                    onShowAll = {
                        onNavAction(DashboardNavRoute.AllTransactionsNavRoute)
                    }
                )
            }

            composable<DashboardNavRoute.AllTransactionsNavRoute> {
                AllTransactionsRoot(appModule)
            }
        }
    }
}

