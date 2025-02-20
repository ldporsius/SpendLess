package nl.codingwithlinda.core.domain.session_manager

import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult

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

    suspend fun login(pin:String, onResult: (SpendResult<Account?, RootError>) -> Unit)
}