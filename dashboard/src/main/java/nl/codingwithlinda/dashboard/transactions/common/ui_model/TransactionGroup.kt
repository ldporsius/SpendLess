package nl.codingwithlinda.dashboard.transactions.common.ui_model

import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.DayDiff

data class TransactionGroup(
    val date: DayDiff,
    val transactions: List<Transaction>

)
