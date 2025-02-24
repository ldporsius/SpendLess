package nl.codingwithlinda.user_settings.security.presentation.state

import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.SessionDuration

data class SecurityUiState(
    val selectedSessionDuration: SessionDuration = SessionDuration.FIVE_MINUTES,
    val selectedLockedOutDuration: LockedOutDuration = LockedOutDuration.FIFTEEN_SECONDS
)
