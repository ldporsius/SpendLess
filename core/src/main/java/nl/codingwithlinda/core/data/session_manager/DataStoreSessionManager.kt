package nl.codingwithlinda.core.data.session_manager

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class DataStoreSessionManager(
    private val context: Context,
): SessionManager {
    override suspend fun setSessionDuration(duration: Long) {

    }

    override suspend fun setUserId(userId: String) {

    }

    override fun getUserId(): Flow<String?> {
        return flowOf(null)
    }

    override fun isUserLoggedIn(): Flow<Boolean> {
        return flowOf(false)
    }
}