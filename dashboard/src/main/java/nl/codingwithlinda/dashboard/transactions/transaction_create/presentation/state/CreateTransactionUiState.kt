package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import nl.codingwithlinda.core.domain.model.TransactionType

data class CreateTransactionUiState(
    val transactionType: TransactionType = TransactionType.EXPENSE,
    val recipient: String = "",
    val amountEntered: String = "",
    val amount: AnnotatedString = AnnotatedString(text = "-$00.00"),
    val amountPlaceholder: AnnotatedString = AnnotatedString(text = "-$00.00"),
    val description: String = ""
){


}
