package nl.codingwithlinda.core.test_data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class FakeSessionManager: SessionManager {
    var sessionDuration: Long = 0L
    var lockedOutDuration: Long = 0L
    var accountId = MutableStateFlow("")

    override suspend fun setSessionDuration(duration: Long) {
        sessionDuration = duration
    }

    override suspend fun getSessionDuration(): Long {
       return sessionDuration
    }

    override suspend fun setAccountId(userId: String) {
        accountId.update {
            userId
        }
    }

    override fun getAccountId(): Flow<String?> {
        return accountId
    }

    override fun isUserLoggedIn(): Flow<Boolean> {
       return accountId.map {
           it.isNotEmpty()
       }
    }

    override suspend fun startSession() {

    }

    override suspend fun endSession() {

    }

    override suspend fun isSessionValid(currentTime: Long): Boolean {
        return true
    }

    override suspend fun setLockedOutDuration(duration: Long) {
        lockedOutDuration = duration
    }

    override suspend fun getLockedOutDuration(): Long {
        return lockedOutDuration
    }

    override suspend fun lockOutUser() {

    }

    override suspend fun unlockUser() {

    }

    override suspend fun isUserLockedOut(currentTime: Long): Boolean {
        return false
    }

    override suspend fun remainingLockoutTime(currentTime: Long): Long {
        return 0L
    }
}