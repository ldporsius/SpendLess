package nl.codingwithlinda.authentication.login.domain.usecase

import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class StartSessionUseCase(
    private val sessionManager: SessionManager
) {

    suspend fun startSession(account: Account){
        sessionManager.setUserId(account.id)
        sessionManager.startSession()
    }
}