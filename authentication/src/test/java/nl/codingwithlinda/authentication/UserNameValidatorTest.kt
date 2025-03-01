package nl.codingwithlinda.authentication

import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.domain.error.authentication_error.AuthenticationError
import nl.codingwithlinda.authentication.validation.UserNameValidator
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.test_data.local_cache.FakeAccountAccess
import nl.codingwithlinda.core.test_data.test_data_generators.fakeAccount
import org.junit.Assert.*
import org.junit.Test

class UserNameValidatorTest{

    private val testAccount = fakeAccount("1")
    private val accountAccess = FakeAccountAccess(listOf(testAccount).toMutableList())
    private val userNameValidator =
        UserNameValidator(accountAccess)

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

        val result = userNameValidator.isUserNameUnique(testAccount.userName)
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

        accountAccess.delete(testAccount.userName to testAccount.pin)
        val result2 = userNameValidator.isUserNameUnique(testAccount.userName)
        println("Result2 $result2")
        assertTrue(result2 is SpendResult.Success)

    }
}