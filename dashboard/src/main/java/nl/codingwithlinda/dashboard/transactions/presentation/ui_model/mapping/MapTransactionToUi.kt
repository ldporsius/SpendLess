package nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping

import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.core_ui.util.scaleToTwoDecimalPlaces
import nl.codingwithlinda.dashboard.categories.presentation.mapping.mapExpenseCategoryIdentifierToDomain
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionUi

fun Transaction.toUi(
    currencyFormatterFactory: CurrencyFormatterFactory,
    preferences: Preferences
): TransactionUi {
    val amountAsDouble = this.amount.scaleToTwoDecimalPlaces()
    val currencyFormatter = currencyFormatterFactory.getFormatter(this.amount)
    return TransactionUi(
        amount = currencyFormatter.formatCurrencyString(amountAsDouble.toString(), preferences),
        timestamp = timestamp.toString(),
        title = title,
        description = description,
        category = mapExpenseCategoryIdentifierToDomain(this.category)
    )
}