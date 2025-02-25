package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state

sealed interface CreateTransactionAction {
    object SaveTransaction : CreateTransactionAction
    object ToggleExpenseIncome : CreateTransactionAction
    data class EnterRecipient(val recipient: String) : CreateTransactionAction
    data class EnterAmount(val amount: String) : CreateTransactionAction
    data class EnterDescription(val description: String) : CreateTransactionAction

}