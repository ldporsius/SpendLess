package nl.codingwithlinda.core_ui.currency

import android.content.Context
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core_ui.incomeColor

class CurrencyFormatterIncomeSymbolOnly(
    override val context: Context
): CurrencyFormatter(
    context
) {

    override fun cleanInput(input: String): String {
        return input.filter { it.isDigit() }
    }
    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {

        val currency = cleanInput(_currency)
        val currencySymbol = applySymbol(currency, preferences)
        val appliedThousandsSeparator = applyThousandsSeparators(currency, preferences)
        val decimalSeparator = applyDecimalSeparators(currency, preferences)

        return buildAnnotatedString {
            withStyle(SpanStyle(
                color = incomeColor
            )) {
                append(currencySymbol)
            }
            append(
                "$appliedThousandsSeparator$decimalSeparator${currency.takeLast(2)}"
            )
        }
    }

}