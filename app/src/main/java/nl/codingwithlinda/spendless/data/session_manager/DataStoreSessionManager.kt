package nl.codingwithlinda.spendless.data.session_manager

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.persistence.datastore.data.UserSessionSerializer.datastore

class DataStoreSessionManager(
    private val context: Context,
): SessionManager {

    private val datastore = context.datastore

    override suspend fun setSessionDuration(duration: Long) {

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