package nl.codingwithlinda.core_ui.currency

import android.content.Context
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.core_ui.expenseColor
import nl.codingwithlinda.core_ui.util.scaleToTwoDecimalPlaces
import java.math.BigDecimal

class CurrencyFormatterExpense(
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

        return when(
            preferences.expensesFormat
        ) {
            ExpensesFormat.MINUS -> {
                buildAnnotatedString {
                    append(
                        AnnotatedString(
                            "-$currencySymbol",
                            spanStyle = SpanStyle(
                                color = expenseColor
                            )
                        )
                    )
                    append(
                        "$appliedThousandsSeparator$decimalSeparator${currency.takeLast(2)}"

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