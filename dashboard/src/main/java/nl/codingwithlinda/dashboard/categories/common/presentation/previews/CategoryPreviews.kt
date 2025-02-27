package nl.codingwithlinda.dashboard.categories.common.presentation.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.dashboard.categories.common.presentation.CategoriesComponent
import nl.codingwithlinda.dashboard.categories.common.presentation.CategoryItem
import nl.codingwithlinda.dashboard.categories.common.presentation.mapping.expenseCategoriesToUi

@Preview
@Composable
private fun PreviewCatComp() {

    val images = expenseCategoriesToUi(
        LocalContext.current
    )
    CategoriesComponent(
        Modifier.fillMaxSize(),
        images
    )
}

@Preview
@Composable
private fun CategoryItemPreview() {
    SpendLessTheme {
        CategoryItem(
            modifier = Modifier.fillMaxSize(),
            expenseCategoryUi = expenseCategoriesToUi(LocalContext.current)[0]
        )

    }
}