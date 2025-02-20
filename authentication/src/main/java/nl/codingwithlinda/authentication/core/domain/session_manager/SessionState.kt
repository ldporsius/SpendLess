package nl.codingwithlinda.authentication.core.domain.session_manager

import nl.codingwithlinda.authentication.core.domain.error.SessionError
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult

interface SessionState {
    suspend fun login(accountId: String, pin: String): SpendResult<Account?, SessionError>
}