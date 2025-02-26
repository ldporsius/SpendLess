package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core_ui.currency.decimalSeparatorMap
import nl.codingwithlinda.core_ui.currency.thousandsSeparatorMap
import nl.codingwithlinda.core_ui.incomeColor
import nl.codingwithlinda.core_ui.onSurface
import nl.codingwithlinda.core_ui.util.scaleToTwoDecimalPlaces
import java.math.BigDecimal

abstract class CurrencyFormatter(
    private val currencySymbolProvider: CurrencySymbolProvider
) {
    abstract fun cleanInput(input: String): String
    fun formatCurrencyString(currency: BigDecimal, preferences: Preferences): AnnotatedString{
        return currency.scaleToTwoDecimalPlaces().let {
               formatCurrencyString(it, preferences)
            }
    }
    abstract fun formatCurrencyString(currency:String, preferences: Preferences): AnnotatedString

    fun applySymbol(preferences: Preferences): String {

        val currencyString = currencySymbolProvider.getCurrencySymbol(preferences)

        return currencyString
    }

    open fun applyThousandsSeparators(currency: String, preferences: Preferences): String {
        val thousandsSeparator = thousandsSeparatorMap[preferences.thousandsSeparator] ?: return currency

        val arrayWholeNumber = currency

            .map { it.toString() }.toMutableList()
        for (i in arrayWholeNumber.size  downTo 1 step 3){
            arrayWholeNumber.add(i, thousandsSeparator)
        }

        val appliedThousandsSeparator = arrayWholeNumber.joinToString("").dropLast(1).padStart(2,'0')

        return appliedThousandsSeparator
    }
    fun getDecimalSeparator(preferences: Preferences): String {
        val decimalSeparator = decimalSeparatorMap[preferences.decimalSeparator] ?: return ""
        return decimalSeparator
    }

    open fun annotatedString(
        toBeAnnotated: String,
        neutral: String,
        neutralColor: Color,
        decimals: String,
        decimalColor: Color = onSurface
    ): AnnotatedString{
        return buildAnnotatedString {
            withStyle(
                SpanStyle(
                color = incomeColor
            )
            ) {
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