package nl.codingwithlinda.user_settings.security.domain.usecase

import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.SessionDuration
import nl.codingwithlinda.core.test_data.FakeSessionManager
import okio.AsyncTimeout.Companion.lock
import org.junit.Assert.*
import org.junit.Test

class GetSecuritySettingsUseCaseTest{

    val sessionManager = FakeSessionManager()
    val useCase = GetSecuritySettingsUseCase(
        sessionManager = sessionManager
    )

    @Test
    fun `test security settings conversions`() = runBlocking {

       val (sess, lock) = useCase.getSettingsForLoggedInUser()

        assertEquals(SessionDuration.FIVE_MINUTES, sess)
        assertEquals(LockedOutDuration.FIFTEEN_SECONDS, lock)

    }

    @Test
    fun `test security settings conversions 2`() = runBlocking {

        sessionManager.setSessionDuration(SessionDuration.FIFTEEN_MINUTES.duration.toLong())
        val (sess, lock) = useCase.getSettingsForLoggedInUser()

        assertEquals(SessionDuration.FIFTEEN_MINUTES, sess)
        assertEquals(LockedOutDuration.FIFTEEN_SECONDS, lock)

    }
}