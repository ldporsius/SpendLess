package nl.codingwithlinda.spendless.data.authentication_manager

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import nl.codingwithlinda.authentication.core.data.session_manager.finite_states.SessionLockedState
import nl.codingwithlinda.authentication.core.data.session_manager.finite_states.SessionUnlockedState
import nl.codingwithlinda.authentication.core.domain.session_manager.SessionState
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.core.domain.session_manager.ESessionState
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class AppAuthenticationManager(
    private val sessionManager: SessionManager,
    private val loginValidator: LoginValidator,
    private val accountAccess: DataSourceAccessReadOnly<Account, String>,
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
        get() = flow {
            while (true) {
                emit(sessionManager.isUserLockedOut(System.currentTimeMillis()))
                delay(1000)
            }
        }

    override val remainingLockoutTime: Flow<Long>
        get() = isLockedOut.transform{
                val remainingTime = sessionManager.remainingLockoutTime(System.currentTimeMillis())
                emit(remainingTime)
            }


    override suspend fun isUserLockedOut(currentTime: Long): Boolean {
        return sessionManager.isUserLockedOut(currentTime)
    }

    override suspend fun login(userName: String, pin: String): SpendResult<Account?, SessionError> {
        toggleLockedState()
        return state.login(userName, pin)
    }

    override suspend fun login(pin: String): SpendResult<Account?, SessionError> {

        toggleLockedState()

        val accountId = sessionManager.getAccountId().firstOrNull() ?: return SpendResult.Failure(SessionError.NoAccountError)

        val result = state.login(accountId = accountId, pin = pin)

        when(result){
            is SpendResult.Failure -> {

                PINPromptAttempts ++

                if(PINPromptAttempts >= 3){
                    sessionManager.lockOutUser()
                    PINPromptAttempts = 0
                }

            }
            is SpendResult.Success -> {
                sessionManager.startSession()
                PINPromptAttempts = 0
            }
        }

        toggleLockedState()

        return result
    }

    override suspend fun logout() {
        sessionManager.endSession()
    }

    override suspend fun handleEvent(): ESessionState {
        val accountId = sessionManager.getAccountId().firstOrNull() ?: return ESessionState.LOGGED_OUT
        accountAccess.getById(accountId) ?: return ESessionState.LOGGED_OUT

        val isLoggedIn = sessionManager.isUserLoggedIn().firstOrNull() ?: return ESessionState.LOGGED_OUT

        val isSessionValid = sessionManager.isSessionValid(System.currentTimeMillis())

        println("AUTH MANAGER IS LOGGED IN: $isLoggedIn")
        println("AUTH MANAGER IS SESSION VALID: $isSessionValid")

        if(isLoggedIn && isSessionValid) return ESessionState.OK

        return ESessionState.SESSION_EXPRIRED

    }

    private suspend fun toggleLockedState(){
        val isLockedOut = sessionManager.isUserLockedOut(System.currentTimeMillis())
        lockedOutStateFlow.emit(isLockedOut)

        state = if(isLockedOut){
            sessionLockedState
        } else{
            sessionUnlockedState
        }
    }
}