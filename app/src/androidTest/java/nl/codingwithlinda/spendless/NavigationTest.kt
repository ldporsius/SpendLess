package nl.codingwithlinda.spendless

import android.app.Application
import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import nl.codingwithlinda.core.test_data.test_data_generators.fakeAccount
import nl.codingwithlinda.core.test_data.test_data_generators.fakePreferencesAccount
import nl.codingwithlinda.spendless.application.SpendLessApplication.Companion.appModule
import nl.codingwithlinda.spendless.navigation.SpendLessApp
import nl.codingwithlinda.spendless.test_data.FakeAppModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Application = ApplicationProvider.getApplicationContext<Application>()

    private lateinit var appModule: FakeAppModule
    private lateinit var navController: TestNavHostController

    private val accounts = listOf(
        fakeAccount("1")
    )
    private val prefsaccounts = listOf(
        fakePreferencesAccount("1")
    )
    @Before
    fun setup(){
        navController = TestNavHostController(context)
        appModule = FakeAppModule(
            accounts = accounts,
            preferencesAccounts = prefsaccounts,
            transactions = emptyList()
        )
    }

    @Test
    fun testNavigation() {
        composeTestRule.setContent {
            SpendLessApp(
                appModule = appModule,
                navHostController = navController,
                onNavAction = {
                    navController.navigate(it)
                }
            )
        }
    }
}