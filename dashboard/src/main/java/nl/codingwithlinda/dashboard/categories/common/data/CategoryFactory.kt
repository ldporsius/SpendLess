package nl.codingwithlinda.dashboard.categories.common.data

import android.content.Context
import nl.codingwithlinda.dashboard.categories.common.presentation.mapping.expenseCategoriesToUi
import nl.codingwithlinda.dashboard.categories.common.presentation.model.ExpenseCategoryUi

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