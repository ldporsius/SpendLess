package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core_ui.incomeColor
import nl.codingwithlinda.core_ui.util.scaleToTwoDecimalPlaces
import nl.codingwithlinda.core_ui.util.stringToBigDecimal
import java.math.BigDecimal
import java.math.BigInteger

class CurrencyFormatterIncome(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {

    override fun cleanInput(input: String): String {
        return input.filter { it.isDigit() }
    }
    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {
        val currencySymbol = applySymbol( preferences)
        val decimalSeparator = getDecimalSeparator(preferences)

        val bd = stringToBigDecimal(_currency)

        val thousands = bd.toBigInteger().toString()
        val decimals = bd.remainder(BigDecimal.ONE).movePointRight(2)
            .toString().padEnd(2, '0')
        println("CURRENCYFORMATTER INCOME. thousands: $thousands, decimals: $decimals")

        val appliedThousandsSeparator = applyThousandsSeparators(thousands, preferences)

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