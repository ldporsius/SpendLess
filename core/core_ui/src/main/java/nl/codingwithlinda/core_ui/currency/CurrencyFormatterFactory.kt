package nl.codingwithlinda.core_ui.currency

import android.content.Context
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.core_ui.currency.formatters.CurrencyFormatter
import nl.codingwithlinda.core_ui.currency.formatters.CurrencyFormatterExpense
import nl.codingwithlinda.core_ui.currency.formatters.CurrencyFormatterIncome
import nl.codingwithlinda.core_ui.currency.formatters.CurrencyFormatterIncomeSymbolOnly
import java.math.BigDecimal

class CurrencyFormatterFactory(
    private val context: Context
) {

    private val currencySymbolProvider = AppCurrencySymbolProvider(context)
    fun getFormatter(transactionType: TransactionType): CurrencyFormatter {
        return when (transactionType) {
            TransactionType.INCOME -> CurrencyFormatterIncome(currencySymbolProvider)
            TransactionType.EXPENSE -> CurrencyFormatterExpense(currencySymbolProvider)
        }
    }
    fun getFormatterSymbolOnly(transactionType: TransactionType): CurrencyFormatter {
        return when (transactionType) {
            TransactionType.INCOME -> CurrencyFormatterIncomeSymbolOnly(currencySymbolProvider)
            TransactionType.EXPENSE -> CurrencyFormatterExpense(currencySymbolProvider)
        }
    }
    fun getFormatter(amount: BigDecimal): CurrencyFormatter {
        if (amount.signum() >= 0)
            return CurrencyFormatterIncome(currencySymbolProvider)
        else
            return CurrencyFormatterExpense(currencySymbolProvider)
    }
}