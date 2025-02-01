package nl.codingwithlinda.core.navigation

import kotlinx.serialization.Serializable


sealed interface AuthenticationNavRoute : NavRoute {
    @Serializable
    data object AuthenticationRoot: AuthenticationNavRoute

    @Serializable
    data object RegisterUserNameRoute : AuthenticationNavRoute

    @Serializable
    data object CreatePinRoute : AuthenticationNavRoute

    @Serializable
    data class RepeatPinRoute(
        val pin: String
    ) : AuthenticationNavRoute

    @Serializable
    data object LoginRoute : AuthenticationNavRoute
}