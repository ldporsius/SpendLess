package nl.codingwithlinda.core.navigation

import androidx.navigation.NavController

fun NavController.navigateToEvent(event: NavigationEvent) {
    when (event) {
        is NavigationEvent.NavToRegisterUserName -> navigate(AuthenticationNavRoute.RegisterUserNameRoute)
        is NavigationEvent.NavToRegisterPin -> navigate(AuthenticationNavRoute.CreatePinRoute)
        is NavigationEvent.NavToLogin -> navigate(AuthenticationNavRoute.LoginRoute)
    }
}