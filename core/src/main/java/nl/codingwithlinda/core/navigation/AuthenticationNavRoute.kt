package nl.codingwithlinda.core.navigation

import kotlinx.serialization.Serializable


sealed interface AuthenticationNavRoute : NavRoute {
    @Serializable
    data object AuthenticationRoot: AuthenticationNavRoute

    @Serializable
    data object RegisterUserNameRoute : AuthenticationNavRoute

    @Serializable
    data class CreatePinRoute(
        val userName: String
    ) : AuthenticationNavRoute

    @Serializable
    data class RepeatPinRoute(
        val userName: String,
        val pin: String
    ) : AuthenticationNavRoute

    @Serializable
    data object OnboardingPreferencesRoute: AuthenticationNavRoute

    @Serializable
    data object LoginRoute : AuthenticationNavRoute
}