package nl.codingwithlinda.core_ui.currency

import android.content.Context
import java.math.BigDecimal

class CurrencyFormatterFactory(
    private val context: Context
) {
    fun getFormatter(amount: BigDecimal): CurrencyFormatter {
        if (amount.signum() >= 0)
            return CurrencyFormatterIncome(context)
        else
            return CurrencyFormatterExpense(context)

    }
}