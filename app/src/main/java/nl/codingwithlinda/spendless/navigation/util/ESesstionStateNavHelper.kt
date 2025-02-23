package nl.codingwithlinda.spendless.navigation.util

import nl.codingwithlinda.core.domain.session_manager.ESessionState
import nl.codingwithlinda.spendless.navigation.authentication.LoginRoute
import nl.codingwithlinda.spendless.navigation.authentication.PINPromptRoute
import nl.codingwithlinda.spendless.navigation.core.destinations.Destination
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute

fun ESessionState.navigate(): NavRoute{
    return when(this){
        ESessionState.OK -> Destination.HomeGraph
        ESessionState.LOGGED_OUT -> LoginRoute
        ESessionState.SESSION_EXPRIRED -> PINPromptRoute
    }
}