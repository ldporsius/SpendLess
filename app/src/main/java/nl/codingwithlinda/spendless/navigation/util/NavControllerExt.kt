package nl.codingwithlinda.spendless.navigation.util

import androidx.navigation.NavHostController
import nl.codingwithlinda.spendless.navigation.core.destinations.AuthenticationNavRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.LoginRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.PINPromptRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.DashboardNavRoute

fun NavHostController.navigateToEvent(event: NavigationEvent) {
    when (event) {
        is NavigationEvent.NavToRegisterUserName -> navigate(AuthenticationNavRoute.RegisterUserNameRoute)
        is NavigationEvent.NavToCreatePin -> navigate(AuthenticationNavRoute.CreatePinRoute(event.userName))
        NavigationEvent.NavToLogin -> navigate(LoginRoute)
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
       is NavigationEvent.NavToPINPrompt -> {
            navigate(
                PINPromptRoute(originalDestination =
                event.originalDestination
            )
            )
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