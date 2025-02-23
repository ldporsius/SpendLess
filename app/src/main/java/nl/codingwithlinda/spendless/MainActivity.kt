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
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import nl.codingwithlinda.core.domain.session_manager.ESessionState
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.DashboardNavRoute
import nl.codingwithlinda.core.navigation.NavRoute
import nl.codingwithlinda.core.presentation.util.ObserveAsEvents
import nl.codingwithlinda.spendless.application.SpendLessApplication
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.spendless.navigation.SpendLessApp
import nl.codingwithlinda.spendless.navigation.util.navigateToEvent

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

            var startDestination: NavRoute by remember {
                mutableStateOf(DashboardNavRoute.DashboardRoot)
            }

            LaunchedEffect(true) {
                viewModel.isSessionValid().let {sessionState ->
                    startDestination = when(sessionState){
                        ESessionState.OK -> {
                            DashboardNavRoute.DashboardRoot
                        }

                        ESessionState.LOGGED_OUT -> {
                            AuthenticationNavRoute.LoginRoute
                        }

                        ESessionState.SESSION_EXPRIRED -> {
                            AuthenticationNavRoute.PINPromptRoute
                        }
                    }
                }
            }


            SpendLessTheme {
                SpendLessApp(
                    appModule = appModule,
                    navHostController = navHostController,
                    startDestination = startDestination,
                    onNavAction = {navRoute ->
                        scope.launch {
                            viewModel.isSessionValid().let { sessionState ->
                                println("MAIN ACTIVITY HAS session state: $sessionState")
                                when (sessionState) {
                                    ESessionState.OK -> {
                                       navHostController.navigate(navRoute)
                                    }

                                    ESessionState.LOGGED_OUT -> {
                                       navHostController.navigate( AuthenticationNavRoute.LoginRoute)
                                    }

                                    ESessionState.SESSION_EXPRIRED -> {
                                        navHostController.navigate(AuthenticationNavRoute.PINPromptRoute)
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
