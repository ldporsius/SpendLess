package nl.codingwithlinda.core.domain.validation

import nl.codingwithlinda.core.domain.error.authentication.AuthenticationError
import nl.codingwithlinda.core.domain.result.SpendResult
import org.junit.Assert.*
import org.junit.Test

class UserNameValidatorTest{

    @Test
    fun `test user input is valid`(){
        val result = UserNameValidator.isUserNameInputValid("lind")
        when(result){
            is SpendResult.Failure -> {
                println("Error ${result.error}")
                assertTrue(result.error is AuthenticationError.UserNameInvalidCharactersError)
            }
            is SpendResult.Success -> {
                println("Success ${result.data}")
                assertTrue(result.data)
            }
        }

    }
}