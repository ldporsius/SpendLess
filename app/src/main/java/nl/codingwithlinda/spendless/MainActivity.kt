package nl.codingwithlinda.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import nl.codingwithlinda.core.domain.session_manager.ESessionState
import nl.codingwithlinda.spendless.navigation.authentication.AuthenticationNavRoute
import nl.codingwithlinda.spendless.navigation.dashboard.DashboardNavRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute
import nl.codingwithlinda.spendless.application.SpendLessApplication
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.spendless.navigation.SpendLessApp
import nl.codingwithlinda.spendless.navigation.authentication.LoginRoute
import nl.codingwithlinda.spendless.navigation.authentication.PINPromptRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

         val appModule = SpendLessApplication.appModule

        setContent {
            val scope = rememberCoroutineScope()

            val navHostController = rememberNavController()

            val factory = viewModelFactory {
                initializer {
                    MainViewModel(
                        authenticationManager = appModule.authenticationManager
                    )
                }
            }
            val viewModel = viewModel<MainViewModel>(
                factory = factory
            )

            LaunchedEffect(true) {
                viewModel.isSessionValid().let {sessionState ->
                    when(sessionState){
                        ESessionState.OK -> {
                            navHostController.navigate(Destination.HomeGraph)
                        }

                        ESessionState.LOGGED_OUT -> {
                            navHostController.navigate(LoginRoute)
                        }

                        ESessionState.SESSION_EXPRIRED -> {
                            navHostController.navigate(PINPromptRoute)
                        }
                    }
                }
            }


            SpendLessTheme {
                SpendLessApp(
                    appModule = appModule,
                    navHostController = navHostController,
                    onNavAction = {navRoute ->
                        scope.launch {
                            viewModel.isSessionValid().let { sessionState ->
                                println("MAIN ACTIVITY HAS session state: $sessionState")
                                when (sessionState) {
                                    ESessionState.OK -> {
                                       navHostController.navigate(navRoute)
                                    }

                                    ESessionState.LOGGED_OUT -> {
                                       navHostController.navigate( LoginRoute)
                                    }

                                    ESessionState.SESSION_EXPRIRED -> {
                                        navHostController.navigate(PINPromptRoute)
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
