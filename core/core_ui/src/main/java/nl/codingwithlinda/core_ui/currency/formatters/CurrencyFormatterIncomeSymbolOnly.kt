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

class CurrencyFormatterIncomeSymbolOnly(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {

    override fun cleanInput(input: String): String {
       return input
    }
    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {
        val currencySymbol = applySymbol(preferences)

        val thousandsSeparator = thousandsSeparatorMap[preferences.thousandsSeparator] ?: ""
        val decimalSeparator = getDecimalSeparator(preferences)

        val currency = _currency
            .replace(thousandsSeparator, "")
            .replace(",", decimalSeparator)
            .replace(".", decimalSeparator)

        println("CURRENCYFORMATTER INCOME SYMBOL ONLY. currency after replace: $currency")
        val thousands = currency.substringBefore(decimalSeparator)


        val appliedThousandsSeparator = applyThousandsSeparators(thousands, preferences)
        val decimals = currency.substringAfter(decimalSeparator, "")
            .padStart(2, '0')

       return annotatedString(
           toBeAnnotated = currencySymbol,
           neutral = "$appliedThousandsSeparator",
           neutralColor = if(_currency.isEmpty()) onSurface.copy(0.5f) else onSurface,
           decimals = "$decimalSeparator$decimals",
           decimalColor = if(currency.contains(decimalSeparator)) onSurface else onSurface.copy(0.5f)
       )
    }

    private fun annotatedString(
        toBeAnnotated: String,
        neutral: String,
        neutralColor: Color,
        decimals: String,
        decimalColor: Color = onSurface
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

    override fun applyThousandsSeparators(currency: String, preferences: Preferences): String {
        val thousandsSeparator = thousandsSeparatorMap[preferences.thousandsSeparator] ?: return currency

        val arrayWholeNumber = currency.map { it.toString() }.toMutableList()
        for (i in arrayWholeNumber.size  downTo 1 step 3){
            arrayWholeNumber.add(i, thousandsSeparator)
        }

        val appliedThousandsSeparator = arrayWholeNumber.joinToString("").dropLast(1).padStart(2,'0')

        return appliedThousandsSeparator


    }

}