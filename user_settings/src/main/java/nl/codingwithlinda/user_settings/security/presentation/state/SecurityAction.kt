package nl.codingwithlinda.user_settings.security.presentation.state

import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.SessionDuration

sealed interface SecurityAction {

    data class SelectSessionDuration(val duration: SessionDuration): SecurityAction
    data class SelectLockedOutDuration(val duration: LockedOutDuration): SecurityAction
    data object Save: SecurityAction


}