package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state

import nl.codingwithlinda.core.domain.model.TransactionType

data class CreateTransactionUiState(
    val transactionType: TransactionType = TransactionType.EXPENSE,
)
