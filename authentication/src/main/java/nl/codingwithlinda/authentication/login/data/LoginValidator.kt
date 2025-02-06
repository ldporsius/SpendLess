package nl.codingwithlinda.authentication.login.data

import nl.codingwithlinda.core.data.AccountFactory
import nl.codingwithlinda.authentication.login.domain.LoginError
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult

class LoginValidator(
    private val accountAccess: DataSourceAccess<Account, Pair<String, String>>,
    private val accountFactory: AccountFactory
) {

    suspend fun validateCredentials(userName: String, pin: String): SpendResult<Boolean, LoginError> {
        val account = accountAccess.read(Pair(userName,""))
            ?: return SpendResult.Failure(LoginError.UserNameUnknownError)

        val pinDecrypted = accountFactory.decrypt(account).pin
        if (pin != pinDecrypted) return SpendResult.Failure(LoginError.WrongPINError)

        return SpendResult.Success(true)

    }
}