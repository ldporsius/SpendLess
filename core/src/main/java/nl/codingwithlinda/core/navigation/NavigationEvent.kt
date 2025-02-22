package nl.codingwithlinda.core.navigation

import androidx.navigation.NavHostController

sealed interface NavigationEvent {
    data object NavToRegisterUserName: NavigationEvent
    data class NavToCreatePin(val userName: String): NavigationEvent
    data class RedirectToDashboard(val navHostController: NavHostController): NavigationEvent
    data object NavToLogin: NavigationEvent

}