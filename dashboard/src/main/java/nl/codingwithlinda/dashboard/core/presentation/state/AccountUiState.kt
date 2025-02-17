package nl.codingwithlinda.dashboard.core.presentation.state

import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionUi

data class AccountUiState(
    val userName: String = "rockefeller74",
    val accountBalance: String = "$10,382.45",
    val mostPopularCategory: ExpenseCategory = ExpenseCategory.HOME,
    val largestTransaction: TransactionUi = TransactionUi(),
    val sumPreviousWeek: String = "-$762.20",
)
