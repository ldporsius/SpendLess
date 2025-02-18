package nl.codingwithlinda.core_ui.currency

import android.content.Context
import nl.codingwithlinda.core.domain.model.Preferences

abstract class CurrencyFormatter(
    open val context: Context
) {
    abstract fun cleanInput(input: String): String
    abstract fun formatCurrencyString(currency:String, preferences: Preferences): String

    fun applySymbol(currency: String, preferences: Preferences): String {
        val currencySymbol = currencySymbolMap[preferences.currency] ?: return currency
        val currencyString = context.getString(currencySymbol)

        return currencyString
    }

    fun applyThousandsSeparators(currency: String, preferences: Preferences): String {
        val thousandsSeparator = thousandsSeparatorMap[preferences.thousandsSeparator] ?: return currency

        val arrayWholeNumber = currency.dropLast(2).map { it.toString() }.toMutableList()
        for (i in arrayWholeNumber.size  downTo 1 step 3){
            arrayWholeNumber.add(i, thousandsSeparator)
        }

        val appliedThousandsSeparator = arrayWholeNumber.joinToString("").dropLast(1)


        return appliedThousandsSeparator
    }
    fun applyDecimalSeparators(currency: String, preferences: Preferences): String {
        val decimalSeparator = decimalSeparatorMap[preferences.decimalSeparator] ?: return currency
        return decimalSeparator
    }
}