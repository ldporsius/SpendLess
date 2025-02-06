package nl.codingwithlinda.dashboard.categories.presentation

import androidx.lifecycle.ViewModel

class CategoriesViewModel(
    private val categoryImageProvider: CategoryImageProvider
): ViewModel() {

    val categoryImages = categoryImageProvider.categoryImages()
}