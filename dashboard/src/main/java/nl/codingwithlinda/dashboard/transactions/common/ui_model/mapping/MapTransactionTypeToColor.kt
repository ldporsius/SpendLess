package nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping

import androidx.compose.ui.graphics.Color
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.core_ui.expenseColor
import nl.codingwithlinda.core_ui.incomeColor

fun TransactionType.toColor(): Color {

    return when(this){
        TransactionType.EXPENSE -> expenseColor
        TransactionType.INCOME -> incomeColor
    }
}