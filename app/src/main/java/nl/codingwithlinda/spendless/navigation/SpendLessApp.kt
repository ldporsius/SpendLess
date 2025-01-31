package nl.codingwithlinda.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.authentication.navigation.authenticationNavGraph
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute

@Composable
fun SpendLessApp() {
   val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = AuthenticationNavRoute.AuthenticationRoot) {

        authenticationNavGraph()
    }
}

