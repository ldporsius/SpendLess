package nl.codingwithlinda.core.navigation

import nl.codingwithlinda.core.domain.model.Account

sealed interface NavigationEvent {
    data object NavToRegisterUserName: NavigationEvent
    data class NavToCreatePin(val userName: String): NavigationEvent
    data class NavToRepeatPin(val userName: String, val pin: String): NavigationEvent
    data object NavToDashboard: NavigationEvent
    data object NavToLogin: NavigationEvent
    data class NavToOnboarding(val account: Account): NavigationEvent
    data object NavToPINPrompt: NavigationEvent
    data object NavToAllTransactions: NavigationEvent

    data object NavUp: NavigationEvent
    data object PopBackStack: NavigationEvent

}