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

    companion object{
        const val NUMBER_PIN_LENGTH = 5
    }
    suspend fun validateCredentials(userName: String, pin: String): SpendResult<Boolean, LoginError> {
        if (userName.isEmpty() || pin.isEmpty())  return SpendResult.Success(false)

        if (pin.length < NUMBER_PIN_LENGTH) return SpendResult.Success(false)

        val account = accountAccess.read(Pair(userName,""))
            ?: return SpendResult.Failure(LoginError.UserNameUnknownError)

        val pinDecrypted = accountFactory.decrypt(account).pin
        if (pin != pinDecrypted) return SpendResult.Failure(LoginError.WrongPINError)

        return SpendResult.Success(true)

    }
}