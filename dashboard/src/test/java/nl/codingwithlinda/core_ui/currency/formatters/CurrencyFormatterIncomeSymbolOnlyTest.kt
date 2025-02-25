package nl.codingwithlinda.core_ui.currency.formatters

import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.test_data.FakeCurrencySymbolProvider
import nl.codingwithlinda.core.test_data.fakePreferences
import org.junit.Assert.*
import org.junit.Test

class CurrencyFormatterIncomeSymbolOnlyTest{
    private val currencySymbolProvider = FakeCurrencySymbolProvider()
    private val formatter = CurrencyFormatterIncomeSymbolOnly(currencySymbolProvider)
    private val preferences = fakePreferences()

    @Test
    fun `test currency formatter`(): Unit = runBlocking {
        val symbol = currencySymbolProvider.getCurrencySymbol(preferences)
        val input = "0000"
        val result = formatter.formatCurrencyString(input, preferences)

        println(preferences)
        assertEquals(result.text, "$symbol$input")
    }
}