package nl.codingwithlinda.dashboard.categories.domain.model

import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core_ui.util.UiText

data class ExpenseCategoryUi(
    val expenseCategory: ExpenseCategory,
    val expenseLabel: UiText,
    val imageText: String
)
