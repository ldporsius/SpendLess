package nl.codingwithlinda.dashboard.transactions.presentation.ui_model

import nl.codingwithlinda.core.domain.model.Transaction

data class TransactionGroup(
    val date: Int,
    val transactions: List<Transaction>

)
