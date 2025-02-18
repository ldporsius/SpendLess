package nl.codingwithlinda.authentication.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.authentication.navigation.authenticationNavGraph
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.DashboardNavRoute

@Composable
fun AuthenticationRootScreen(
    appModule: AppModule,
    navHostController: NavHostController
) {

    val navController = rememberNavController()
    Scaffold {paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController,
                startDestination = AuthenticationNavRoute.LoginRoute){
                authenticationNavGraph(
                    navHostController = navHostController,
                    navController = navController,
                    appModule = appModule
                )

            }
        }
    }
}