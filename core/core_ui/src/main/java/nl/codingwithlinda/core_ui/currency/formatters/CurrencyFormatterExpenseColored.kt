package nl.codingwithlinda.core_ui.currency.formatters

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core_ui.expenseColor
import nl.codingwithlinda.core_ui.incomeColor
import nl.codingwithlinda.core_ui.onSurface
import nl.codingwithlinda.core_ui.util.stringToBigDecimal
import java.math.BigDecimal
import java.math.BigInteger

class CurrencyFormatterExpenseColored(
    currencySymbolProvider: CurrencySymbolProvider,
): CurrencyFormatter(
    currencySymbolProvider
) {
    override fun cleanInput(input: String): String {
        return input.replace(",", ".")
    }

    override fun formatCurrencyString(_currency:String, preferences: Preferences): AnnotatedString {

        println("CURRENCY FORMATTER EXPENSE COLORED HAS ORIGINAL CURRENCY STRING: $_currency")
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

        return when(
            preferences.expensesFormat
        ) {
            ExpensesFormat.MINUS -> {

                annotatedString(
                    toBeAnnotated = "-$currencySymbol",
                    neutral = "$appliedThousandsSeparator",
                    neutralColor = neutralColor,
                    decimals = "$decimalSeparator${decimals}",
                    decimalColor = decimalColor
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
                    append("$appliedThousandsSeparator$decimalSeparator${decimals}")
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

    override fun annotatedString(
        toBeAnnotated: String,
        neutral: String,
        neutralColor: Color,
        decimals: String,
        decimalColor: Color
    ): AnnotatedString{
        return buildAnnotatedString {
            withStyle(SpanStyle(
                color = expenseColor
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