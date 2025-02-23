package nl.codingwithlinda.spendless.data.session_manager

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.core.domain.session_manager.SessionManager.Companion.DEFAULT_SESSION_DURATION
import nl.codingwithlinda.core.domain.session_manager.SessionManager.Companion.LOCKED_OUT_DURATION
import nl.codingwithlinda.core.domain.session_manager.SessionManager.Companion.MAX_NUMBER_LOGIN_ATTEMPTS
import nl.codingwithlinda.persistence.datastore.data.UserSessionSerializer.datastore
import kotlin.time.Duration.Companion.milliseconds

class DataStoreSessionManager(
    context: Context,
): SessionManager {

    private val datastore = context.datastore

    override suspend fun setSessionDuration(duration: Long) {
        datastore.updateData {
            it.copy(
                sessionDuration = duration
            )
        }
    }
    override suspend fun getSessionDuration(): Long {
        return datastore.data.map {
            it.sessionDuration
        }.firstOrNull() ?: DEFAULT_SESSION_DURATION
    }

    override suspend fun setAccountId(userId: String) {
        datastore.updateData {
            it.copy(
                accountId = userId
            )
        }
    }

    override fun getAccountId(): Flow<String?> {
        return datastore.data.map {
            it.accountId
        }
    }

    override fun isUserLoggedIn(): Flow<Boolean> {
        return datastore.data.map {
            it.accountId.isNotEmpty()
        }
    }

    override suspend fun startSession() {
       datastore.updateData {
           it.copy(
               sessionStartTime = System.currentTimeMillis()
           )
       }
    }
    override suspend fun endSession() {
        datastore.updateData {
            it.copy(
                accountId = "",
                sessionStartTime = 0L
            )
        }
    }


    override suspend fun lockOutUser() {
        datastore.updateData {
            it.copy(
                numberLoginAttempts = MAX_NUMBER_LOGIN_ATTEMPTS,
                lockedOutStartTime = System.currentTimeMillis()
            )
        }
    }

    override suspend fun unlockUser() {
       datastore.updateData {
           it.copy(
               numberLoginAttempts = 0,
               lockedOutStartTime = 0L
           )
       }
    }

    override suspend fun isUserLockedOut(currentTime: Long): Boolean {
        datastore.data.map {
            it.lockedOutStartTime
        }.firstOrNull()?.let {lockedOutTime ->
          return currentTime - lockedOutTime < LOCKED_OUT_DURATION
        }
        return false
    }

    override suspend fun remainingLockoutTime(currentTime: Long): Long {
        return datastore.data.map {
            it.lockedOutStartTime
        }.firstOrNull()?.let {lockedOutStartTime ->
           val remaining =
               SessionManager.Companion.LOCKED_OUT_DURATION.milliseconds
                   .minus(currentTime.milliseconds - lockedOutStartTime.milliseconds)

            remaining.inWholeMilliseconds
        } ?: LOCKED_OUT_DURATION.milliseconds.inWholeMilliseconds
    }


    override suspend fun isSessionValid(currentTime: Long): Boolean {
        val durationSettings = getSessionDuration()
        val durationInMinutes = durationSettings.milliseconds.inWholeMinutes

        val startTime = datastore.data.map {
            it.sessionStartTime
        }.firstOrNull() ?: 0L

        val diffTimeInMinutes = currentTime.milliseconds.inWholeMinutes - startTime.milliseconds.inWholeMinutes

        val isSessionValid = diffTimeInMinutes < durationInMinutes

        return  isSessionValid
    }
}