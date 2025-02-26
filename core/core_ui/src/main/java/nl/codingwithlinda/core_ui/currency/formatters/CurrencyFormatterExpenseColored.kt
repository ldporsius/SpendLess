package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core_ui.expenseColor

class CurrencyFormatterExpenseColored(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {
    override fun cleanInput(input: String): String {
        return input.filter { it.isDigit() }
    }

    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {

        val currency = cleanInput(_currency)
        val currencySymbol = applySymbol(preferences)
        val appliedThousandsSeparator = applyThousandsSeparators(currency, preferences)
        val decimalSeparator = getDecimalSeparator(preferences)

        return when(
            preferences.expensesFormat
        ) {
            ExpensesFormat.MINUS -> {
                buildAnnotatedString {
                    append(
                        "-$currencySymbol$appliedThousandsSeparator$decimalSeparator${currency.takeLast(2)}"
                    )
                }
            }

            ExpensesFormat.BRACKETS -> {
                AnnotatedString(
                "($currencySymbol$appliedThousandsSeparator$decimalSeparator${currency.takeLast(2)})"
                )
            }
        }
    }

}