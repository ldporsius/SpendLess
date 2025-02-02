package nl.codingwithlinda.spendless.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.authentication.core.presentation.AuthenticationRootScreen
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.DashboardNavRoute

@Composable
fun SpendLessApp(
    appModule: AppModule
) {
   val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = AuthenticationNavRoute.AuthenticationRoot) {

        composable<AuthenticationNavRoute.AuthenticationRoot> {
            AuthenticationRootScreen(
                appModule = appModule,
                navHostController = navHostController
            )
        }

        composable<DashboardNavRoute.DashboardRoot>{

            Text("Dashboard")
        }
    }
}

