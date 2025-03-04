package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state

import androidx.compose.ui.text.AnnotatedString
import nl.codingwithlinda.core.domain.model.TransactionType

data class CreateTransactionUiState(
    val transactionType: TransactionType = TransactionType.EXPENSE,
    val recipient: String = "",
    val amountEntered: String = "",
    val amount: AnnotatedString = AnnotatedString(text = ""),
    val amountPlaceholder: AnnotatedString = AnnotatedString(text = ""),
    val description: String = ""

){

    fun recipientPlaceholder(): String{
        return when(transactionType){
            TransactionType.EXPENSE -> "Receiver"
            TransactionType.INCOME -> "Sender"
        }
    }

    fun descriptionText(): String{
        return if (description.isEmpty()){
            " + Add Note"
        }else description
    }

}
