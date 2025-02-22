package nl.codingwithlinda.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.navigation.DashboardNavRoute
import nl.codingwithlinda.core.navigation.NavRoute
import nl.codingwithlinda.dashboard.core.presentation.DashboardRoot
import nl.codingwithlinda.dashboard.transactions.transactions_all.presentation.AllTransactionsRoot
import nl.codingwithlinda.spendless.navigation.authentication.authenticationNavGraph
import nl.codingwithlinda.spendless.navigation.util.navigateToEvent

@Composable
fun SpendLessApp(
    appModule: AppModule,
    navHostController: NavHostController,
    startDestination: NavRoute,
    onNavAction: (NavRoute) -> Unit
) {

    NavHost(navController = navHostController, startDestination = startDestination) {

        authenticationNavGraph(
            appModule = appModule,
            onNavAction = {
                navHostController.navigateToEvent(it)
            }
        )

        composable<DashboardNavRoute.DashboardRoot>{

            DashboardRoot(
                appModule = appModule,
                onShowAll = {
                    onNavAction(DashboardNavRoute.AllTransactionsNavRoute)
                }
            )
        }

        composable<DashboardNavRoute.AllTransactionsNavRoute>{
            AllTransactionsRoot(appModule)
        }
    }
}

