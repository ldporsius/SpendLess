package nl.codingwithlinda.core.domain.session_manager

import kotlinx.coroutines.flow.Flow

interface SessionManager {

    fun isUserLoggedIn(): Flow<Boolean>
}