package nl.codingwithlinda.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import nl.codingwithlinda.core.navigation.DashboardNavRoute
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

            LaunchedEffect(true) {
                navHostController.navigate(DashboardNavRoute.DashboardRoot)
            }
            SpendLessTheme {
                SpendLessApp(appModule, navHostController)
            }
        }
    }
}
