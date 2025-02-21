package nl.codingwithlinda.authentication.core.data.session_manager.finite_states

import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.authentication.core.domain.session_manager.SessionState
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError

class SessionLockedState(

): SessionState {
    override suspend fun loginWithNameAndPin(
        userName: String,
        pin: String,
    ): SpendResult<Account?, SessionError> {
        return SpendResult.Failure(error = SessionError.SessionLockedError)
    }

    override suspend fun login(accountId: String, pin: String): SpendResult<Account?, SessionError> {
        return SpendResult.Failure(error = SessionError.SessionLockedError)
    }
}