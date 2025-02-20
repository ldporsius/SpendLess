package nl.codingwithlinda.spendless.data.session_manager

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.persistence.datastore.data.UserSessionSerializer.datastore

class DataStoreSessionManager(
    private val context: Context,
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
        }.firstOrNull() ?: 15_000L
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
}