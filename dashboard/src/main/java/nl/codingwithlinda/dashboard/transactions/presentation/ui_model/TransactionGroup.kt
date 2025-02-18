package nl.codingwithlinda.dashboard.transactions.presentation.ui_model

import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.DayDiff

data class TransactionGroup(
    val date: DayDiff,
    val transactions: List<Transaction>

)
