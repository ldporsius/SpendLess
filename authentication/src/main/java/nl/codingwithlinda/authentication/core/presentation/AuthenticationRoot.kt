package nl.codingwithlinda.authentication.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.authentication.navigation.authenticationNavGraph
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute

@Composable
fun AuthenticationRootScreen(
    appModule: AppModule
) {

    val navController = rememberNavController()
    Scaffold {paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController,
                startDestination = AuthenticationNavRoute.RegisterUserNameRoute){
                authenticationNavGraph(
                    navController = navController,
                    appModule = appModule
                )
            }
        }
    }
}