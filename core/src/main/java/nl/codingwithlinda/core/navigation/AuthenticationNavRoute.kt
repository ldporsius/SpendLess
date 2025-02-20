package nl.codingwithlinda.core.navigation

import kotlinx.serialization.Serializable
import nl.codingwithlinda.core.domain.model.Account


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
    data class OnboardingPreferencesRoute(
        val account: Account
    ): AuthenticationNavRoute

    @Serializable
    data object WelcomeBackRoute : AuthenticationNavRoute

    @Serializable
    data object LoginRoute : AuthenticationNavRoute
}