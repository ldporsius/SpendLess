package nl.codingwithlinda.spendless.navigation.core

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import nl.codingwithlinda.spendless.navigation.core.actions.NavigationAction
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination

class DefaultNavigator(
    override val startDestination: Destination,
): Navigator{
    private val _navigationActions = Channel<NavigationAction>()
    override val navigationActions: Flow<NavigationAction>
        get() = _navigationActions.receiveAsFlow()

    override suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit,
    ) {
       _navigationActions.send(
           NavigationAction.Navigate(
               destination = destination,
               navOptions = navOptions
           )
       )
    }

    override suspend fun navigateUp() {
        _navigationActions.send(NavigationAction.NavigateUp)
    }
}