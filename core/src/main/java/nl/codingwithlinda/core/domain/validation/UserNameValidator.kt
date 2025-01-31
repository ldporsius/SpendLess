package nl.codingwithlinda.core.domain.validation

import nl.codingwithlinda.core.domain.error.authentication.AuthenticationError
import nl.codingwithlinda.core.domain.error.authentication.UserNameDuplicateError
import nl.codingwithlinda.core.domain.result.SpendResult

object UserNameValidator {

    val MIN_LENGTH = 3
    val MAX_LENGTH = 14

    fun isUserNameInputValid(userName: String): SpendResult<Boolean, AuthenticationError> {

        if (userName.length < MIN_LENGTH) return SpendResult.Failure(AuthenticationError.UserNameShortError)
        if (userName.length > MAX_LENGTH) return SpendResult.Failure(AuthenticationError.UserNameLongError)

        val isOnlyLettersAndNumbers = userName.matches("^[a-zA-Z0-9]*$".toRegex())
        if (!isOnlyLettersAndNumbers) return SpendResult.Failure(AuthenticationError.UserNameInvalidCharactersError)
        return SpendResult.Success(true)

    }

    fun isUserNameUnique(userName: String): SpendResult<Boolean, UserNameDuplicateError> {
        return SpendResult.Success(true)
    }
}