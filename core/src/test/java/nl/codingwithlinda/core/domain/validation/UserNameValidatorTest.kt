package nl.codingwithlinda.core.domain.validation

import nl.codingwithlinda.core.domain.error.authentication.AuthenticationError
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.fake_data.FakeAccountAccess
import org.junit.Assert.*
import org.junit.Test

class UserNameValidatorTest{

    private val accountAccess = FakeAccountAccess()
    private val userNameValidator = UserNameValidator(accountAccess)

    @Test
    fun `test user input is valid`(){
        val result = userNameValidator.isUserNameInputValid("lind")
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