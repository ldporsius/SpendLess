package nl.codingwithlinda.authentication.core.domain.session_manager

import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult

interface SessionState {
    suspend fun loginWithNameAndPin(userName:String, pin:String): SpendResult<Account?, SessionError>

    suspend fun login(accountId: String, pin: String): SpendResult<Account?, SessionError>
}