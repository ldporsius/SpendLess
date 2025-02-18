package nl.codingwithlinda.dashboard.transactions.presentation.ui_model

import nl.codingwithlinda.core_ui.util.UiText

data class TransactionGroupUi(
    val header: UiText,
    val transactions: List<TransactionUi>
)
