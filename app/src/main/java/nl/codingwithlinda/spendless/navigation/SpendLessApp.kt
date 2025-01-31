package nl.codingwithlinda.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.authentication.core.presentation.AuthenticationRootScreen
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute

@Composable
fun SpendLessApp() {
   val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = AuthenticationNavRoute.AuthenticationRoot) {

        composable<AuthenticationNavRoute.AuthenticationRoot> {
            AuthenticationRootScreen()
        }

    }
}

