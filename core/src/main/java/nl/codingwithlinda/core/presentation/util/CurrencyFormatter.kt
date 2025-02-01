package nl.codingwithlinda.core.presentation.util

import android.content.Context
import nl.codingwithlinda.core.data.currency_format.decimalSeparatorMap
import nl.codingwithlinda.core.data.currency_format.thousandsSeparatorMap
import nl.codingwithlinda.core.domain.CurrencyFormatter
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences

class CurrencyFormatterImpl(
    private val context: Context
): CurrencyFormatter {

    override fun formatCurrencyString(currency:String, preferences: Preferences): String {

        val currencySymbol = currencySymbolMap[preferences.currency] ?: return currency
        val currencyString = context.getString(currencySymbol)

        val thousandsSeparator = thousandsSeparatorMap[preferences.thousandsSeparator] ?: return currency
        val decimalSeparator = decimalSeparatorMap[preferences.decimalSeparator] ?: return currency

        val arrayWholeNumber = currency.dropLast(2).map { it.toString() }.toMutableList()
        for (i in arrayWholeNumber.size  downTo 1 step 3){
            arrayWholeNumber.add(i, thousandsSeparator)
        }

        val appliedThousandsSeparator = arrayWholeNumber.joinToString("").dropLast(1)


        return when(
            preferences.expensesFormat
        ) {
            ExpensesFormat.MINUS -> {
                "-$currencyString$appliedThousandsSeparator$decimalSeparator${currency.takeLast(2)}"
            }

            ExpensesFormat.BRACKETS -> {
                "($currencyString$appliedThousandsSeparator$decimalSeparator${currency.takeLast(2)})"
            }
        }

    }
}