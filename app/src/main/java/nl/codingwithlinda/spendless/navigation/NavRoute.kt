package nl.codingwithlinda.spendless.navigation

import kotlinx.serialization.Serializable


interface NavRoute


sealed interface AuthenticationNavRoute : NavRoute {
    @Serializable
    data object AuthenticationRoot: AuthenticationNavRoute
    @Serializable
    data object RegisterUserNameRoute : AuthenticationNavRoute
    @Serializable
    data object CreatePinRoute : AuthenticationNavRoute
    @Serializable
    data object LoginRoute : AuthenticationNavRoute
}