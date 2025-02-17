package nl.codingwithlinda.dashboard.transactions.presentation.ui_model

import nl.codingwithlinda.core.domain.model.ExpenseCategory

data class TransactionUi(
    val amount: String = "-$59.99",
    val timestamp: String = "Jan 7, 2025",
    val title: String = "Adobe Yearly",
    val description: String = "Enjoyed a coffee and a snack at Starbucks with Rick and M.",
    val category: ExpenseCategory = ExpenseCategory.HOME,

    )
