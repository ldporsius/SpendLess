package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core_ui.currency.thousandsSeparatorMap
import nl.codingwithlinda.core_ui.incomeColor

class CurrencyFormatterIncomeSymbolOnly(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {

    override fun cleanInput(input: String): String {
        val noLetters = input.filter { !it.isLetter() }
            val beforeDecimalSep = noLetters.substringBefore('.')
        val afterDecimalSep = noLetters.substringAfter('.')
        return "${beforeDecimalSep}.${afterDecimalSep}"
    }
    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {
        val currencySymbol = applySymbol(preferences)

        val thousandsSeparator = thousandsSeparatorMap[preferences.thousandsSeparator] ?: ""
        val decimalSeparator = getDecimalSeparator(preferences)

        val currency = _currency
            .replace(thousandsSeparator, "")
            .replace(",", decimalSeparator)
            .replace(".", decimalSeparator)


        val appliedThousandsSeparator = applyThousandsSeparators(currency, preferences)
        val decimals = currency.takeLast(2).padStart(2, '0')

       return annotatedString(
           toBeAnnotated = currencySymbol,
           neutral = "$appliedThousandsSeparator$decimalSeparator$decimals"
       )
    }

    private fun annotatedString(
        toBeAnnotated: String,
        neutral: String
    ): AnnotatedString{
        return buildAnnotatedString {
            withStyle(SpanStyle(
                color = incomeColor
            )) {
                append(toBeAnnotated)
            }
            append(
               neutral
            )
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