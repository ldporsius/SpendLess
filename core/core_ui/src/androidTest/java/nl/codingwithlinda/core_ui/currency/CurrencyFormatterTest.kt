package nl.codingwithlinda.core_ui.currency

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core_ui.currency.formatters.CurrencyFormatterExpense
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyFormatterTest{

    val context = ApplicationProvider.getApplicationContext<Application>()
    private val currencyFormatter = CurrencyFormatterExpense(context)
    val preferences = Preferences(
        expensesFormat = ExpensesFormat.MINUS,
        currency = Currency.EURO,
        thousandsSeparator = Separator.PERIOD,
        decimalSeparator = Separator.COMMA,
        decimalPlaces = 2
    )

    val currencySymbol = currencySymbolMap.get(preferences.currency)

    @Test
    fun test_currency_format(){
        val currency = "1038245"
        val currencyString = context.getString(currencySymbol!!)

        println("CURRENCY SYMBOL: $currencyString")

        val result = currencyFormatter.formatCurrencyString(currency, preferences)

        assertEquals("-${currencyString}10.382,45", result)

    }
}