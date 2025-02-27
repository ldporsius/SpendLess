package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core_ui.currency.thousandsSeparatorMap
import nl.codingwithlinda.core_ui.incomeColor
import nl.codingwithlinda.core_ui.onSurface
import nl.codingwithlinda.core_ui.primary
import nl.codingwithlinda.core_ui.util.stringToBigDecimal
import java.math.BigDecimal
import java.math.BigInteger

class CurrencyFormatterIncomeSymbolOnly(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {

    override fun cleanInput(input: String): String {
        return input.replace(",", ".")
    }
    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {
        val currencySymbol = applySymbol(preferences)
        val decimalSeparator = getDecimalSeparator(preferences)

        val currency = cleanInput(_currency)
        val (thousands, decimals) = convertAmountToThousandsAndDecimals(currency, preferences)

        val appliedThousandsSeparator = applyThousandsSeparators(thousands, preferences)

        val grayedOut = currency.isEmpty()
        val hasDecimalSep = currency.contains(decimalSeparator)
        val neutralColor = if(grayedOut) onSurface.copy(0.5f) else onSurface
        val decimalColor = if (grayedOut) onSurface.copy(0.5f)
        else
            if(hasDecimalSep) onSurface else onSurface.copy(0.5f)

       return annotatedString(
           toBeAnnotated = currencySymbol,
           neutral = "$appliedThousandsSeparator",
           neutralColor = neutralColor,
           decimals = "$decimalSeparator$decimals",
           decimalColor = decimalColor
       )
    }

    override fun annotatedString(
        toBeAnnotated: String,
        neutral: String,
        neutralColor: Color,
        decimals: String,
        decimalColor: Color
    ): AnnotatedString{
        return buildAnnotatedString {
            withStyle(SpanStyle(
                color = incomeColor
            )) {
                append(toBeAnnotated)
            }
            withStyle(
                SpanStyle(
                    color = neutralColor
                )
            ) {
                append(
                    neutral
                )
            }
            withStyle(
                SpanStyle(
                    color = decimalColor
                )
            ) {
                append(
                    decimals
                )
            }
        }
    }


}