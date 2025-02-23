package nl.codingwithlinda.spendless.navigation.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.firstOrNull
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute
import nl.codingwithlinda.spendless.navigation.util.NavigationEvent

@Composable
fun AuthenticationRoot(
    appModule: AppModule,
    onNavAction: (NavigationEvent) -> Unit
) {

    val sessionManager = appModule.sessionManager

    var startDestination: NavRoute by remember {
        mutableStateOf(AuthenticationNavRoute.AuthenticationRoot)
    }

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = startDestination
    ){
        authenticationNavGraph(
            //navController = navController,
            appModule = appModule,
            onNavAction = onNavAction
        )
    }

}