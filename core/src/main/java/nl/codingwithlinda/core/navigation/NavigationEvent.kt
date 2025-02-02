package nl.codingwithlinda.core.navigation

sealed interface NavigationEvent {
    data object NavToRegisterUserName: NavigationEvent
    data class NavToCreatePin(val userName: String): NavigationEvent
   data object RedirectToDashboard: NavigationEvent

}