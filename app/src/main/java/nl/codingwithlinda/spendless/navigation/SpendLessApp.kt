package nl.codingwithlinda.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.spendless.navigation.graph.AuthenticationRootScreen
import nl.codingwithlinda.authentication.registration.presentation.RegisterUserNameScreen

@Composable
fun SpendLessApp() {
   val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = AuthenticationNavRoute.AuthenticationRoot) {

        authenticationNavGraph()
    }
}

fun NavGraphBuilder.authenticationNavGraph() {
    composable<AuthenticationNavRoute.AuthenticationRoot> {
      AuthenticationRootScreen()
    }
    composable<AuthenticationNavRoute.RegisterUserNameRoute> {
        RegisterUserNameScreen()
    }

}