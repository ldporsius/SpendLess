package nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping

import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.dashboard.categories.presentation.mapping.mapExpenseCategoryIdentifierToDomain
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionUi

fun Transaction.toUi(): TransactionUi {
    return TransactionUi(
        amount = amount.toString(),
        timestamp = timestamp.toString(),
        title = title,
        description = description,
        category = mapExpenseCategoryIdentifierToDomain(this.category)
    )
}