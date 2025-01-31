package nl.codingwithlinda.core.navigation

sealed interface NavigationEvent {
    data object NavToRegisterUserName: NavigationEvent
    data object NavToRegisterPin: NavigationEvent
    data object NavToLogin: NavigationEvent

}