package nl.codingwithlinda.spendless.navigation.util

import nl.codingwithlinda.core.domain.session_manager.ESessionState
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination
import nl.codingwithlinda.spendless.navigation.core.destinations.LoginRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.PINPromptRoute

fun ESessionState.navigate(
    originalDestination: NavRoute
): NavRoute{
    return when(this){
        ESessionState.OK -> Destination.HomeGraph
        ESessionState.LOGGED_OUT -> LoginRoute
        ESessionState.SESSION_EXPRIRED -> PINPromptRoute(
            originalDestination = originalDestination
        )
    }
}