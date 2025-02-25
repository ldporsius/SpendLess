package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state

sealed interface CreateTransactionAction {
    object SaveTransaction : CreateTransactionAction
    object ToggleExpenseIncome : CreateTransactionAction

}