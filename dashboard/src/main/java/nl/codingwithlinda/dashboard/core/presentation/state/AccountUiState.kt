package nl.codingwithlinda.dashboard.core.presentation.state

import nl.codingwithlinda.dashboard.categories.presentation.model.ExpenseCategoryUi
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionUi

data class AccountUiState(
    val userName: String = "rockefeller74",
    val accountBalance: String = "$10,382.45",
    val mostPopularCategory: ExpenseCategoryUi? = null,
    val largestTransaction: TransactionUi? = null,
    val sumPreviousWeek: String = "-$762.20",
)
