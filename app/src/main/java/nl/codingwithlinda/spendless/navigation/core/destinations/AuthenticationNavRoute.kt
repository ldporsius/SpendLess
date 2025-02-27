package nl.codingwithlinda.spendless.navigation.core.destinations

import kotlinx.serialization.Serializable
import nl.codingwithlinda.core.domain.model.Account

@Serializable
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

}

@Serializable
data class PINPromptRoute(val originalDestination: NavRoute):NavRoute
@Serializable
data object LoginRoute : NavRoute