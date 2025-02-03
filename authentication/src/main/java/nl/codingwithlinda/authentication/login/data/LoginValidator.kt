package nl.codingwithlinda.authentication.login.data

import nl.codingwithlinda.core.domain.error.login.LoginError
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult

class LoginValidator(
    private val accountAccess: DataSourceAccess<Account, Pair<String, String>>
) {

    suspend fun validateCredentials(userName: String, pin: String): SpendResult<Boolean, LoginError> {
        val account = accountAccess.read(Pair(userName,""))
            ?: return SpendResult.Failure(LoginError.UserNameUnknownError)

        if (account.pin != pin) return SpendResult.Failure(LoginError.WrongPINError)

        return SpendResult.Success(true)

    }
}