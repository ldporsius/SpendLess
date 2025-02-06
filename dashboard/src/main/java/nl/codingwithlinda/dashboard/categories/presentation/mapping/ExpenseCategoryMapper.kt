package nl.codingwithlinda.dashboard.categories.presentation.mapping

import android.content.Context
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core_ui.util.UiText
import nl.codingwithlinda.dashboard.categories.domain.model.ExpenseCategoryUi

fun expenseCategoriesToUi(context: Context): List<ExpenseCategoryUi>{
    return ExpenseCategory.entries.map {
        ExpenseCategoryUi(
            expenseCategory = it,
            expenseLabel = mapExpenseCategoryToUiText().getOrDefault(it, UiText.DynamicText("")),
            imageText = categoryImages(context).getOrDefault(it, "")
        )
    }
}

fun mapExpenseCategoryToUiText() = mapOf(
        ExpenseCategory.CLOTHING_ACCESSORIES to UiText.DynamicText("Clothing & Accessories"),
        ExpenseCategory.EDUCATION to UiText.DynamicText("Education"),
        ExpenseCategory.ENTERTAINMENT to UiText.DynamicText("Entertainment"),
        ExpenseCategory.FOOD_GROCERIES to UiText.DynamicText("Food & Groceries"),
        ExpenseCategory.HEALTH_WELLNESS to UiText.DynamicText("Health & Wellness")
)
