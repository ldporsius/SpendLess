package nl.codingwithlinda.core_ui.currency

import android.content.Context
import nl.codingwithlinda.core.domain.model.TransactionType
import java.math.BigDecimal

class CurrencyFormatterFactory(
    private val context: Context
) {
    fun getFormatter(transactionType: TransactionType): CurrencyFormatter {
        return when (transactionType) {
            TransactionType.INCOME -> CurrencyFormatterIncome(context)
            TransactionType.EXPENSE -> CurrencyFormatterExpense(context)
        }
    }
    fun getFormatterSymbolOnly(transactionType: TransactionType): CurrencyFormatter {
        return when (transactionType) {
            TransactionType.INCOME -> CurrencyFormatterIncomeSymbolOnly(context)
            TransactionType.EXPENSE -> CurrencyFormatterExpense(context)
        }
    }
    fun getFormatter(amount: BigDecimal): CurrencyFormatter {
        if (amount.signum() >= 0)
            return CurrencyFormatterIncome(context)
        else
            return CurrencyFormatterExpense(context)

    }
}