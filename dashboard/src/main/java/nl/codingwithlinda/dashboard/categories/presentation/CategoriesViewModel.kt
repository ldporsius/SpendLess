package nl.codingwithlinda.dashboard.categories.presentation

import androidx.lifecycle.ViewModel
import nl.codingwithlinda.dashboard.categories.data.CategoryFactory

class CategoriesViewModel(
    private val categoryFactory: CategoryFactory
): ViewModel() {

    val categoryImages = categoryFactory.expenseCategoriesUi()
}