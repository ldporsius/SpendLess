package nl.codingwithlinda.dashboard.transactions.common.ui_model

import android.content.Context
import androidx.compose.ui.text.AnnotatedString
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core_ui.util.UiText
import nl.codingwithlinda.dashboard.categories.common.presentation.mapping.expenseCategoriesToUi
import java.util.UUID

data class TransactionUi(
    //val id: String,
    val amount: AnnotatedString = AnnotatedString("-$59.99"),
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
