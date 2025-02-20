package nl.codingwithlinda.authentication.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.firstOrNull
import nl.codingwithlinda.authentication.navigation.authenticationNavGraph
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.DashboardNavRoute
import nl.codingwithlinda.core.navigation.NavRoute

@Composable
fun AuthenticationRootScreen(
    appModule: AppModule,
    navHostController: NavHostController
) {

    val sessionManager = appModule.sessionManager

    var startDestination: NavRoute by remember {
        mutableStateOf(AuthenticationNavRoute.AuthenticationRoot)
    }
    LaunchedEffect(true) {

        sessionManager.isUserLoggedIn().firstOrNull().let{
            startDestination = if (it == true) {
                AuthenticationNavRoute.WelcomeBackRoute
            } else {
                AuthenticationNavRoute.LoginRoute
            }
        }
    }
    val navController = rememberNavController()
    Scaffold {paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController,
                startDestination = AuthenticationNavRoute.WelcomeBackRoute
            ){
                authenticationNavGraph(
                    navHostController = navHostController,
                    navController = navController,
                    appModule = appModule
                )

            }
        }
    }
}