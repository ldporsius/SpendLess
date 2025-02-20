package nl.codingwithlinda.core.navigation

import androidx.navigation.NavController

fun NavController.navigateToEvent(event: NavigationEvent) {
    when (event) {
        is NavigationEvent.NavToRegisterUserName -> navigate(AuthenticationNavRoute.RegisterUserNameRoute)
        is NavigationEvent.NavToCreatePin -> navigate(AuthenticationNavRoute.CreatePinRoute(event.userName))
        NavigationEvent.RedirectToDashboard -> navigate(DashboardNavRoute.DashboardRoot)
        NavigationEvent.NavToLogin -> navigate(AuthenticationNavRoute.LoginRoute)
    }
}