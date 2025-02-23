package nl.codingwithlinda.spendless.navigation.core.destinations

import kotlinx.serialization.Serializable

sealed interface Destination: NavRoute {

    @Serializable
    data object AuthenticationGraph: Destination

    @Serializable
    data object HomeGraph: Destination

}