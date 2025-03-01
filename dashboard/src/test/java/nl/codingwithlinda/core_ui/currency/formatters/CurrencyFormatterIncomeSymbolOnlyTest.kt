package nl.codingwithlinda.core_ui.currency.formatters

import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core.test_data.FakeCurrencySymbolProvider
import nl.codingwithlinda.core.test_data.test_data_generators.fakePreferences
import org.junit.Assert.*
import org.junit.Test

class CurrencyFormatterIncomeSymbolOnlyTest{
    private val currencySymbolProvider = FakeCurrencySymbolProvider()
    private val formatter = CurrencyFormatterIncomeSymbolOnly(currencySymbolProvider)
    private val preferences = fakePreferences()

    @Test
    fun `test currency formatter - thousands separator is period`(): Unit = runBlocking {
        val symbol = currencySymbolProvider.getCurrencySymbol(preferences)
        val input = "12,34"
        val result = formatter.formatCurrencyString(input, preferences)

        val expected = "${symbol}12,34"
        println(preferences)
        assertEquals(expected, result.text)
    }

    @Test
    fun `test currency formatter - thousands separator is comma`(): Unit = runBlocking {

        val preferences = fakePreferences().copy(
            thousandsSeparator = Separator.COMMA,
            decimalSeparator = Separator.PERIOD
        )
        val symbol = currencySymbolProvider.getCurrencySymbol(preferences)
        val input = "12,34"
        val result = formatter.formatCurrencyString(input, preferences)

        val expected = "${symbol}1,234.00"
        println(preferences)
        assertEquals(expected, result.text)
    }
}