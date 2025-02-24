package nl.codingwithlinda.user_settings.security.domain.usecase

import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.SessionDuration
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class GetSecuritySettingsUseCase(
    private val sessionManager: SessionManager
) {

    suspend fun getSettingsForLoggedInUser(
    ) : Pair<SessionDuration, LockedOutDuration>{
       val sessInMillis = sessionManager.getSessionDuration()
        val sessDuration = SessionDuration.entries.find {
            it.duration == sessInMillis.milliseconds.inWholeMilliseconds.toInt()
        }?: SessionDuration.FIVE_MINUTES

        val lockInMillis = sessionManager.getLockedOutDuration()
       val lockOutDuration = LockedOutDuration.entries.find {
           it.duration == lockInMillis.milliseconds.inWholeMilliseconds.toInt()

       }?: LockedOutDuration.FIFTEEN_SECONDS

        return Pair(sessDuration, lockOutDuration)
    }
}