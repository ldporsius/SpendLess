package nl.codingwithlinda.dashboard.core.presentation.state

import nl.codingwithlinda.dashboard.core.presentation.ui_model.TransactionUi

data class AccountUiState(
    val userName: String = "rockefeller74",
    val accountBalance: String = "$10,382.45",
    val largestTransaction: TransactionUi = TransactionUi(),
    val sumPreviousWeek: String = "-$762.20",
)
