package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core_ui.expenseColor
import nl.codingwithlinda.core_ui.onSurface

class CurrencyFormatterExpenseColored(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {
    override fun cleanInput(input: String): String {
        return input
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

                annotatedString(
                    toBeAnnotated = "-$currencySymbol",
                    neutral = "$appliedThousandsSeparator",
                    neutralColor =if(_currency.isEmpty()) onSurface.copy(0.5f) else onSurface,
                    decimals = "$decimalSeparator${currency.takeLast(2)}",
                    decimalColor = if(currency.contains(decimalSeparator)) onSurface else onSurface.copy(0.5f)
                )

            }

            ExpensesFormat.BRACKETS -> {
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = expenseColor
                        )
                    ) {
                        append("($currencySymbol")
                    }
                    append("$appliedThousandsSeparator$decimalSeparator${currency.takeLast(2)}")
                     withStyle(
                        style = SpanStyle(
                            color = expenseColor
                        )
                    ) {
                        append(")")
                    }

                }

            }
        }
    }

}