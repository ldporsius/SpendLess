package nl.codingwithlinda.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.DashboardNavRoute
import nl.codingwithlinda.core.navigation.NavRoute
import nl.codingwithlinda.spendless.application.SpendLessApplication
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.spendless.navigation.SpendLessApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appModule = SpendLessApplication.appModule

        setContent {
            val navHostController = rememberNavController()

            val factory = viewModelFactory {
                initializer {
                    MainViewModel(
                        sessionManager = appModule.sessionManager
                    )
                }
            }
            val viewModel = androidx.lifecycle.viewmodel.compose.viewModel<MainViewModel>(
                factory = factory
            )

            var startDestination:NavRoute by remember {
                mutableStateOf(AuthenticationNavRoute.AuthenticationRoot)
            }
            LaunchedEffect(true) {

                viewModel.isUserLoggedIn.collect {
                    startDestination = if (it) {
                        DashboardNavRoute.DashboardRoot
                    } else {
                        AuthenticationNavRoute.AuthenticationRoot
                    }
                }
            }

            SpendLessTheme {
                SpendLessApp(
                    appModule = appModule,
                    navHostController = navHostController,
                    startDestination = startDestination
                )
            }
        }
    }
}
