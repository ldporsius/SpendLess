package nl.codingwithlinda.authentication.core.data.session_manager.finite_states

import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.authentication.core.domain.session_manager.SessionState
import nl.codingwithlinda.core.domain.error.authentication_error.LoginError
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError

class SessionUnlockedState(
    private val loginValidator: LoginValidator,
    private val accountAccess: DataSourceAccessReadOnly<Account, String>
): SessionState {
    override suspend fun loginWithNameAndPin(
        userName: String,
        pin: String,
    ): SpendResult<Account?, SessionError> {

        val result = loginValidator.validateCredentials(userName, pin)

        return when(result){
            is SpendResult.Failure -> SpendResult.Failure(SessionError.LoginFailedError(result.error))
            is SpendResult.Success -> SpendResult.Success(result.data)
        }
    }


    override suspend fun login(accountId: String, pin: String): SpendResult<Account?, SessionError> {
        val account = accountAccess.getById(accountId) ?:
        return SpendResult.Failure(SessionError.NoAccountError)

        val result = loginValidator.validateCredentials(account.userName, pin)

        return when(result){
            is SpendResult.Failure -> {
                when(result.error){
                    LoginError.UserNameUnknownError -> SpendResult.Failure(SessionError.LoginFailedError(result.error))
                    LoginError.WrongPINError -> SpendResult.Failure(SessionError.WrongPINError)
                }
            }
            is SpendResult.Success -> SpendResult.Success(result.data)
        }

    }

}