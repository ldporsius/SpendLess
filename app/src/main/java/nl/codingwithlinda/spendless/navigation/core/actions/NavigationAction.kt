package nl.codingwithlinda.spendless.navigation.core.actions

import androidx.navigation.NavOptionsBuilder
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination

sealed interface NavigationAction {

    data class Navigate(
        val destination: Destination,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ): NavigationAction

    data object NavigateUp: NavigationAction
}