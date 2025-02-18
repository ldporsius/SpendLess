package nl.codingwithlinda.dashboard.categories.data

import android.content.Context
import nl.codingwithlinda.dashboard.categories.presentation.mapping.expenseCategoriesToUi
import nl.codingwithlinda.dashboard.categories.presentation.model.ExpenseCategoryUi

class CategoryFactory(
    private val context: Context
) {

    private val expenseCategoriesUi = expenseCategoriesToUi(context)

    fun getCategoryUi(identifier: Int): ExpenseCategoryUi?{
        return expenseCategoriesUi.find {
            it.expenseCategory.identifier == identifier
        }
    }
}