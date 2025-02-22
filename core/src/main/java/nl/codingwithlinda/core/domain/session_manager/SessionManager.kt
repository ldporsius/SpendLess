package nl.codingwithlinda.core.domain.session_manager

import kotlinx.coroutines.flow.Flow

interface SessionManager {

    suspend fun setSessionDuration(duration: Long)
    suspend fun getSessionDuration(): Long
    suspend fun setUserId(userId: String)
    fun getUserId(): Flow<String?>
    fun isUserLoggedIn(): Flow<Boolean>

    suspend fun startSession()
    suspend fun endSession()
    suspend fun isSessionValid(currentTime: Long, ): Boolean

    suspend fun lockOutUser()
    suspend fun unlockUser()
    suspend fun isUserLockedOut(currentTime: Long): Boolean
    suspend fun remainingLockoutTime(currentTime: Long): Long

    companion object{
        const val DEFAULT_SESSION_DURATION = 60_000 * 5L
        const val MAX_NUMBER_LOGIN_ATTEMPTS = 3
        const val LOCKED_OUT_DURATION = 30_000
    }
}