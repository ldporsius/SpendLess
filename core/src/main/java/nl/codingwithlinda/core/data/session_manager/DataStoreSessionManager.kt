package nl.codingwithlinda.core.data.session_manager

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class DataStoreSessionManager(
    private val context: Context,
): SessionManager {

    //todo: replace with datastore
    override fun isUserLoggedIn(): Flow<Boolean> {
        return flowOf(false)
    }
}