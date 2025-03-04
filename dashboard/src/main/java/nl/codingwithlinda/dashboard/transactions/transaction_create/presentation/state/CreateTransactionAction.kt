package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state

import nl.codingwithlinda.core.domain.model.Transaction

sealed interface CreateTransactionAction {
    object CreateTransaction : CreateTransactionAction
    data class SaveTransaction(val transaction: Transaction) : CreateTransactionAction
    object ToggleExpenseIncome : CreateTransactionAction
    data class EnterRecipient(val recipient: String) : CreateTransactionAction
    data class EnterAmount(val amount: String) : CreateTransactionAction
    data class EnterDescription(val description: String) : CreateTransactionAction

}