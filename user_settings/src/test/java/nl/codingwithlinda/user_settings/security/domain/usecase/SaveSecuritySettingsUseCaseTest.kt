package nl.codingwithlinda.user_settings.security.domain.usecase

import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.SessionDuration
import nl.codingwithlinda.core.test_data.FakeSessionManager
import org.junit.Assert.*
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds

class SaveSecuritySettingsUseCaseTest{

    val sessionManager = FakeSessionManager()
    val useCase = SaveSecuritySettingsUseCase(
        sessionManager = sessionManager
    )

    @Test
    fun `test security settings conversions`() = runBlocking {
        useCase.save(
            sessionDuration = SessionDuration.FIVE_MINUTES,
            lockedOutDuration = LockedOutDuration.FIFTEEN_SECONDS
        )

        val sessionDuration = sessionManager.getSessionDuration().milliseconds.inWholeMinutes
        val lockedOutDuration = sessionManager.getLockedOutDuration()

        assertEquals(5L, sessionDuration)
        assertEquals(15_000L, lockedOutDuration)
    }

    @Test
    fun `test security settings conversions 2`() = runBlocking {
        useCase.save(
            sessionDuration = SessionDuration.ONE_HOUR,
            lockedOutDuration = LockedOutDuration.FIVE_MINUTES
        )

        val sessionDuration = sessionManager.getSessionDuration().milliseconds.inWholeHours
        val lockedOutDuration = sessionManager.getLockedOutDuration().milliseconds.inWholeMinutes

        assertEquals(1L, sessionDuration)
        assertEquals(5L, lockedOutDuration)
    }
}