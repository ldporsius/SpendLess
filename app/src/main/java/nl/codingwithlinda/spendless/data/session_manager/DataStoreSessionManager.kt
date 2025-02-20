package nl.codingwithlinda.spendless.data.session_manager

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.persistence.datastore.data.UserSessionSerializer.datastore

class DataStoreSessionManager(
    private val context: Context,
): SessionManager {

    companion object{
        const val DEFAULT_SESSION_DURATION = 60_000 * 5L
    }

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

    override suspend fun setUserId(userId: String) {
        datastore.updateData {
            it.copy(
                userId = userId
            )
        }
    }

    override fun getUserId(): Flow<String?> {
        return datastore.data.map {
            it.userId
        }
    }

    override fun isUserLoggedIn(): Flow<Boolean> {
        return datastore.data.map {
            it.userId.isNotEmpty()
        }
    }

    override suspend fun startSession() {
       datastore.updateData {
           it.copy(
               sessionStartTime = System.currentTimeMillis()
           )
       }
    }
    override suspend fun isSessionValid(currentTime: Long): Boolean {
        val startTime = datastore.data.map {
            it.sessionStartTime
        }.firstOrNull() ?: 0L
        val durationSettings = getSessionDuration()
        return startTime + durationSettings < currentTime
    }
}