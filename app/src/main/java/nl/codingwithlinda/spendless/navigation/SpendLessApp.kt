package nl.codingwithlinda.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nl.codingwithlinda.authentication.core.presentation.AuthenticationRootScreen
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.DashboardNavRoute
import nl.codingwithlinda.core.navigation.NavRoute
import nl.codingwithlinda.dashboard.presentation.DashboardRoot

@Composable
fun SpendLessApp(
    appModule: AppModule,
    navHostController: NavHostController,
    startDestination: NavRoute
) {


    NavHost(navController = navHostController, startDestination = startDestination) {

        composable<AuthenticationNavRoute.AuthenticationRoot> {
            AuthenticationRootScreen(
                appModule = appModule,
                navHostController = navHostController
            )
        }

        composable<DashboardNavRoute.DashboardRoot>{
           DashboardRoot()
        }
    }
}

