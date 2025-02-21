package nl.codingwithlinda.core.domain.session_manager

import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult

interface AuthenticationManager {

    val isLockedOut: Flow<Boolean>
    suspend fun login(userName:String, pin:String): SpendResult<Account?, SessionError>
    suspend fun login(pin:String): SpendResult<Account?, SessionError>
    suspend fun logout()

    suspend fun <T: Any>handleEvent(event: T): SpendResult<T, SessionError>
}