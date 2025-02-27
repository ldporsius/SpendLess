package nl.codingwithlinda.dashboard.categories.category_picker.state

import nl.codingwithlinda.core.domain.model.ExpenseCategory

sealed interface CategoryAction {
    data class SelectCategory(val category: ExpenseCategory) : CategoryAction

}