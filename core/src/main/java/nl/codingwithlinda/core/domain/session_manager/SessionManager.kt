package nl.codingwithlinda.core.domain.session_manager

import kotlinx.coroutines.flow.Flow

interface SessionManager {

    suspend fun setSessionDuration(duration: Long)
    suspend fun setUserId(userId: String)
    fun getUserId(): Flow<String?>
    fun isUserLoggedIn(): Flow<Boolean>
}