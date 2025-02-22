package nl.codingwithlinda.core_ui.currency

import android.content.Context
import nl.codingwithlinda.core.domain.model.Preferences

class CurrencyFormatterIncome(
    override val context: Context
): CurrencyFormatter(
    context
) {

    override fun cleanInput(input: String): String {
        return input.filter { it.isDigit() }
    }
    override fun formatCurrencyString(_currency:String, preferences: Preferences): String {

        val currency = cleanInput(_currency)
        val currencySymbol = applySymbol(currency, preferences)
        val appliedThousandsSeparator = applyThousandsSeparators(currency, preferences)
        val decimalSeparator = applyDecimalSeparators(currency, preferences)

        return "$currencySymbol$appliedThousandsSeparator$decimalSeparator${currency.takeLast(2)}"
    }

}