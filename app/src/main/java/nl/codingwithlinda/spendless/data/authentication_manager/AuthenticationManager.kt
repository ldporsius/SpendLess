package nl.codingwithlinda.spendless.data.authentication_manager

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.authentication.core.data.session_manager.finite_states.SessionLockedState
import nl.codingwithlinda.authentication.core.data.session_manager.finite_states.SessionUnlockedState
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.authentication.core.domain.session_manager.SessionState
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class AppAuthenticationManager(
    private val sessionManager: SessionManager,
    private val loginValidator: LoginValidator,
    accountAccess: DataSourceAccessReadOnly<Account, String>,
    ): AuthenticationManager {

    private val sessionLockedState = SessionLockedState()
    private val sessionUnlockedState = SessionUnlockedState(
        loginValidator = loginValidator,
        accountAccess = accountAccess
    )

    private var PINPromptAttempts = 0
    private var state: SessionState = sessionLockedState
    private val lockedOutStateFlow = MutableStateFlow(false)
    override val isLockedOut: Flow<Boolean>
        get() = lockedOutStateFlow

    override suspend fun login(userName: String, pin: String): SpendResult<Account?, SessionError> {
        toggleLockedState()
        return state.login(userName, pin)
    }

    override suspend fun login(pin: String): SpendResult<Account?, SessionError> {

        toggleLockedState()

        val accountId = sessionManager.getUserId().firstOrNull() ?: return SpendResult.Failure(SessionError.NoAccountError)

        val result = state.login(accountId = accountId, pin = pin)

        when(result){
            is SpendResult.Failure -> {
                if(result.error is SessionError.LoginFailedError){

                    PINPromptAttempts ++

                    if(PINPromptAttempts >= 3){
                        sessionManager.lockOutUser()
                        PINPromptAttempts = 0
                    }
                }
            }
            is SpendResult.Success -> {
                PINPromptAttempts = 0
            }
        }

        toggleLockedState()

        return result
    }

    override suspend fun logout() {
        sessionManager.endSession()
    }

    override suspend fun <T : Any> handleEvent(event: T): SpendResult<T, SessionError> {
        val isSessionValid = sessionManager.isSessionValid(System.currentTimeMillis())
        toggleLockedState()

        return if(isSessionValid){
            SpendResult.Success(event)
        }
        else {
            SpendResult.Failure(SessionError.SessionExpiredError)
        }
    }

    private suspend fun toggleLockedState(){
        val isLockedOut = sessionManager.isUserLockedOut(System.currentTimeMillis())
        lockedOutStateFlow.emit(isLockedOut)

        if(isLockedOut){
            state = sessionLockedState
        }
        else{
            state = sessionUnlockedState
        }
    }
}