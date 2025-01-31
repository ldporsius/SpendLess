package nl.codingwithlinda.spendless.navigation.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.spendless.navigation.AuthenticationNavRoute
import nl.codingwithlinda.spendless.navigation.authenticationNavGraph

@Composable
fun AuthenticationRootScreen() {

    val navController = rememberNavController()
    Scaffold {paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController, startDestination = AuthenticationNavRoute.RegisterUserNameRoute){
                authenticationNavGraph()
            }
        }
    }
}