package nl.codingwithlinda.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.core.presentation.AuthenticationRoot
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.DashboardNavRoute
import nl.codingwithlinda.core.navigation.NavRoute
import nl.codingwithlinda.dashboard.core.presentation.DashboardRoot
import nl.codingwithlinda.dashboard.transactions.transactions_all.presentation.AllTransactionsRoot

@Composable
fun SpendLessApp(
    appModule: AppModule,
    navHostController: NavHostController,
    startDestination: NavRoute
) {

    val authenticationManager = appModule.authenticationManager

    NavHost(navController = navHostController, startDestination = startDestination) {

        composable<AuthenticationNavRoute.AuthenticationRoot> {
            AuthenticationRoot(
                appModule = appModule,
                navHostController = navHostController
            )
        }

        composable<DashboardNavRoute.DashboardRoot>{

            val scope = rememberCoroutineScope()
           DashboardRoot(
               appModule = appModule,
               onShowAll = {
                   scope.launch {
                       authenticationManager.handleEvent(
                           navHostController.navigate(DashboardNavRoute.AllTransactionsNavRoute)
                       ).also {res ->
                           when(
                               res
                           ) {
                               is SpendResult.Failure -> {

                               }
                               is SpendResult.Success -> TODO()
                           }

                       }
                   }

               }
           )
        }

        composable<DashboardNavRoute.AllTransactionsNavRoute>{
            AllTransactionsRoot(appModule)
        }
    }
}

