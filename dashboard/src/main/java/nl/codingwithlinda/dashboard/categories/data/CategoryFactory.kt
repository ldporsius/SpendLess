package nl.codingwithlinda.dashboard.categories.data

import android.content.Context
import nl.codingwithlinda.dashboard.categories.presentation.mapping.expenseCategoriesToUi

class CategoryFactory(
    private val context: Context
) {

    fun expenseCategoriesUi() = expenseCategoriesToUi(context)
}