package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core.data.util.stringToBigDecimal
import java.math.BigDecimal

class CurrencyFormatterExpense(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {
    override fun cleanInput(input: String): String {
        return input.filter { it.isDigit() }
    }

    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {

        val currencySymbol = applySymbol(preferences)
        val decimalSeparator = getDecimalSeparator(preferences)

        val bd = stringToBigDecimal(_currency)

        val thousands = bd.toBigInteger().toString()
        val decimals = bd.remainder(BigDecimal.ONE).movePointRight(2)
            .toString().padEnd(2, '0')
        println("CURRENCYFORMATTER INCOME. thousands: $thousands, decimals: $decimals")

        val appliedThousandsSeparator = applyThousandsSeparators(thousands, preferences)

        return when(
            preferences.expensesFormat
        ) {
            ExpensesFormat.MINUS -> {
                buildAnnotatedString {
                    append(
                        "-$currencySymbol$appliedThousandsSeparator$decimalSeparator${decimals}"
                    )
                }
            }

            ExpensesFormat.BRACKETS -> {
                AnnotatedString(
                "($currencySymbol$appliedThousandsSeparator$decimalSeparator${decimals})"
                )
            }
        }
    }

}