package nl.codingwithlinda.dashboard.transactions.common.ui_model

import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.DayDiff
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.TransactionKey

data class TransactionGroup(
    val date: TransactionKey,
    val transactions: List<Transaction>

)
