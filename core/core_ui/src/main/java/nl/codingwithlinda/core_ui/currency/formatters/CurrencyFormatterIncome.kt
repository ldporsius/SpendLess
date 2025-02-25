package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core_ui.currency.CurrencySymbolProvider
import nl.codingwithlinda.core_ui.incomeColor

class CurrencyFormatterIncome(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {

    override fun cleanInput(input: String): String {
        return input.filter { it.isDigit() }
    }
    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {

        val currency = cleanInput(_currency)
        val currencySymbol = applySymbol( preferences)
        val appliedThousandsSeparator = applyThousandsSeparators(currency, preferences)
        val decimalSeparator = getDecimalSeparator(preferences)
        val decimals = currency.takeLast(2).padEnd(2, '0')

        return buildAnnotatedString {
            withStyle(SpanStyle(
                color = incomeColor
            )){
                append(currencySymbol)

                append(
                    "$appliedThousandsSeparator$decimalSeparator${decimals}"
                )
            }
        }

    }

}