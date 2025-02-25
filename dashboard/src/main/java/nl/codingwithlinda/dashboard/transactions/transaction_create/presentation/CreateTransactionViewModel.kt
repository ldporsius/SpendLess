package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state.CreateTransactionAction
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state.CreateTransactionUiState

class CreateTransactionViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(CreateTransactionUiState())
    val uiState = _uiState.asStateFlow()

    fun handleAction(action: CreateTransactionAction){
        when(action) {
            CreateTransactionAction.SaveTransaction -> {

            }
            CreateTransactionAction.ToggleExpenseIncome -> {
                _uiState.update {
                    it.copy(
                        transactionType = toggleTransactionType()
                    )
                }
            }
        }
    }

    private fun toggleTransactionType(): TransactionType{
        return when(_uiState.value.transactionType){
            TransactionType.INCOME -> TransactionType.EXPENSE
            TransactionType.EXPENSE -> TransactionType.INCOME
        }
    }
}