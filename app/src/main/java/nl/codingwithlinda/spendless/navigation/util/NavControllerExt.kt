package nl.codingwithlinda.spendless.navigation.util

import androidx.navigation.NavHostController
import nl.codingwithlinda.core.navigation.AuthenticationNavRoute
import nl.codingwithlinda.core.navigation.DashboardNavRoute
import nl.codingwithlinda.core.navigation.NavigationEvent

fun NavHostController.navigateToEvent(event: NavigationEvent) {
    when (event) {
        is NavigationEvent.NavToRegisterUserName -> navigate(AuthenticationNavRoute.RegisterUserNameRoute)
        is NavigationEvent.NavToCreatePin -> navigate(AuthenticationNavRoute.CreatePinRoute(event.userName))
        NavigationEvent.NavToLogin -> navigate(AuthenticationNavRoute.LoginRoute)
        NavigationEvent.NavToDashboard -> {
           navigate(
                DashboardNavRoute.DashboardRoot
            )
        }

        is NavigationEvent.NavToAllTransactions -> {
            navigate(
                DashboardNavRoute.AllTransactionsNavRoute
            )

        }
        is NavigationEvent.NavToOnboarding -> {
            navigate(
                AuthenticationNavRoute.OnboardingPreferencesRoute(event.account)
            )
        }
        NavigationEvent.NavToPINPrompt -> {
            navigate(AuthenticationNavRoute.PINPromptRoute)
        }
        is NavigationEvent.NavToRepeatPin -> {
            navigate(AuthenticationNavRoute.RepeatPinRoute(event.userName, event.pin))
        }
        NavigationEvent.NavUp -> {
            navigateUp()
        }
        NavigationEvent.PopBackStack -> {
            popBackStack()
        }
    }
}