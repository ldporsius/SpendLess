package nl.codingwithlinda.dashboard.transactions.presentation.ui_model

import android.content.Context
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core_ui.util.UiText
import nl.codingwithlinda.dashboard.categories.presentation.mapping.expenseCategoriesToUi
import java.util.UUID

data class TransactionUi(
    val id: String = UUID.randomUUID().toString(),
    val amount: String = "-$59.99",
    val timestamp: String = "Jan 7, 2025",
    val title: String = "Adobe Yearly",
    val description: String = "Enjoyed a coffee and a snack at Starbucks with Rick and M.",
    val category: ExpenseCategory = ExpenseCategory.HOME,
    ){

    fun imageText(
        context: Context
    ): String{
        return expenseCategoriesToUi(context).find {
            it.expenseCategory == category
        }?.imageText ?: ""
    }

    fun expenseLabel(
        context: Context
    ): UiText {
        return expenseCategoriesToUi(context).find {
            it.expenseCategory == category
        }?.expenseLabel ?: UiText.DynamicText("no label")
    }
}
