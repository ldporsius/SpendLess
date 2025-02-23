package nl.codingwithlinda.spendless.navigation.core

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.spendless.navigation.core.actions.NavigationAction
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination

interface Navigator {
    val startDestination: Destination
    val navigationActions: Flow<NavigationAction>

    suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )

    suspend fun navigateUp()
}