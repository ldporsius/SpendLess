package nl.codingwithlinda.dashboard.categories.common.data

import android.content.Context
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.dashboard.categories.common.presentation.mapping.expenseCategoriesToUi
import nl.codingwithlinda.dashboard.categories.common.presentation.model.ExpenseCategoryUi
import java.math.BigDecimal

class CategoryFactory(
    private val context: Context
) {

    private val expenseCategoriesUi = expenseCategoriesToUi(context)

    fun getCategoryUi(identifier: Int): ExpenseCategoryUi?{
        return expenseCategoriesUi.find {
            it.expenseCategory.identifier == identifier
        }
    }

    fun hackToDisplayIncomeCategory(amount: BigDecimal, identifier: Int): ExpenseCategoryUi {
        if (amount > BigDecimal.ZERO) {
            return getCategoryUi(
            ExpenseCategory.INCOME.identifier
            ) ?: throw Exception("No income category found")

        }
        return getCategoryUi(identifier) ?: throw Exception("No category found")
    }
}