package nl.codingwithlinda.user_settings.security.domain.usecase

import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.SessionDuration
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class SaveSecuritySettingsUseCase(
    private val sessionManager: SessionManager
) {

    suspend fun save(
        sessionDuration: SessionDuration,
        lockedOutDuration: LockedOutDuration
    ) {
       val sessInMillis = sessionDuration.duration.toLong()

        sessionManager.setSessionDuration(sessInMillis)

        val lockInMillis = lockedOutDuration.duration.toLong()
        sessionManager.setLockedOutDuration(lockInMillis)

    }
}