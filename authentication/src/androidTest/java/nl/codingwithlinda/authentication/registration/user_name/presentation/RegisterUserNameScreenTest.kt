package nl.codingwithlinda.authentication.registration.user_name.presentation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.authentication.registration.user_name.presentation.state.RegisterUserViewState
import nl.codingwithlinda.core.presentation.util.UiText
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class RegisterUserNameScreenTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    private val uistate = RegisterUserViewState(
        userNameDuplicateError = UiText.DynamicText("Username already exists"),
    )
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testRegisterUserNameScreen(): Unit = runBlocking{
        composeTestRule.runOnUiThread {
            composeTestRule.setContent {
                RegisterUserNameScreen(
                    uistate = uistate,
                    onAction = {},
                    onNavigate = {}
                )
            }
        }
        composeTestRule.awaitIdle()
        composeTestRule.waitUntilAtLeastOneExists(
            hasText("Welcome to SpendLess!", true, true),
            timeoutMillis = 5000
        )
        composeTestRule.onNodeWithText("Welcome to SpendLess!", true, true).assertIsDisplayed()

        composeTestRule.waitUntilAtLeastOneExists(
            hasText("Username already exists", true, true),
            timeoutMillis = 5000
        )
        composeTestRule.onNodeWithText("Username already exists", true, true).assertIsDisplayed()

        delay(5000)
    }
}