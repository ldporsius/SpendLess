package nl.codingwithlinda.spendless.data.authentication_manager

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.domain.session_manager.SessionManager.Companion.LOCKED_OUT_DURATION
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class AppAuthenticationManagerTest{

    @Test
    fun testRemainingLockoutTime() = runBlocking {
        val currentTime = System.currentTimeMillis().milliseconds

        val startTime = currentTime - 10.seconds
        val remaining = LOCKED_OUT_DURATION.milliseconds - (currentTime - startTime)

        println(remaining)
        assertTrue(remaining == 20.seconds)

        delay(1000)

        val remaining2 =
            LOCKED_OUT_DURATION.milliseconds - (System.currentTimeMillis().milliseconds - startTime)


        println(remaining2)
        assertTrue(remaining2 == 19.seconds)
    }
}