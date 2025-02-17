package nl.codingwithlinda.dashboard.core.presentation.ui_model

import nl.codingwithlinda.core.domain.model.ExpenseCategory

data class TransactionUi(
    val amount: String = "-$59.99",
    val timestamp: String = "Jan 7, 2025",
    val description: String = "Adobe Yearly",
    val category: ExpenseCategory = ExpenseCategory.HOME,

    )
