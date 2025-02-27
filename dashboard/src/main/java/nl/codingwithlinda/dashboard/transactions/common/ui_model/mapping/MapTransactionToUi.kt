package nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping

import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.core_ui.date_time.timestampToString
import nl.codingwithlinda.core_ui.util.scaleToTwoDecimalPlaces
import nl.codingwithlinda.dashboard.categories.common.presentation.mapping.mapExpenseCategoryIdentifierToDomain
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionUi

fun Transaction.toUi(
    currencyFormatterFactory: CurrencyFormatterFactory,
    preferences: Preferences
): TransactionUi {
    val amountAsDouble = this.amount.scaleToTwoDecimalPlaces()
    val currencyFormatter = currencyFormatterFactory.getFormatter(this.amount)
    return TransactionUi(
        amount = currencyFormatter.formatCurrencyString(amountAsDouble, preferences),
        timestamp = timestampToString(this.timestamp),
        title = title,
        description = description,
        category = mapExpenseCategoryIdentifierToDomain(this.category)
    )
}