package nl.codingwithlinda.core.domain.validation

import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.domain.error.authentication.AuthenticationError
import nl.codingwithlinda.core.domain.error.authentication.UserNameDuplicateError
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

    @Test
    fun `test user name duplicate`(): Unit = runBlocking{

        val result = userNameValidator.isUserNameUnique("lin")
        println("Result $result")
        assertTrue(result is SpendResult.Failure)

        when(result){
            is SpendResult.Failure -> {
                println("Error ${result.error}")

            }
            is SpendResult.Success -> {
                println("Success ${result.data}")
                assertTrue(result.data)
            }
        }

        accountAccess.delete("lin" to "12345")
        val result2 = userNameValidator.isUserNameUnique("lin")
        println("Result2 $result2")
        assertTrue(result2 is SpendResult.Success)

    }
}