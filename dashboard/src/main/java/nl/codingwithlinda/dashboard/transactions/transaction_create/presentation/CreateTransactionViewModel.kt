package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.core.data.dto.TransactionDto
import nl.codingwithlinda.core.data.dto.toDomain
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.core_ui.util.stringToThousandsAndDecimals
import nl.codingwithlinda.dashboard.categories.category_picker.state.CategoryAction
import nl.codingwithlinda.dashboard.core.domain.usecase.PreferencesForAccountUseCase
import nl.codingwithlinda.dashboard.transactions.transaction_create.domain.usecase.CreateTransactionUseCase
import nl.codingwithlinda.dashboard.transactions.transaction_create.domain.usecase.SaveTransactionUseCase
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state.CreateTransactionAction
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state.CreateTransactionUiState

class CreateTransactionViewModel(
    private val currencyFormatterFactory: CurrencyFormatterFactory,
    private val preferencesForAccountUseCase: PreferencesForAccountUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val saveTransactionUseCase: SaveTransactionUseCase,
    private val onNavAction: (transactionDto: TransactionDto?) -> Unit
): ViewModel() {

    private val _amountEntered = MutableStateFlow("")
    private val _pickedCategory = MutableStateFlow<ExpenseCategory>(ExpenseCategory.OTHER)
    private val _prefs = preferencesForAccountUseCase.preferencesForLoggedInAccount().mapNotNull{ res ->
        when(res){
            is SpendResult.Failure -> null
            is SpendResult.Success -> res.data.preferences
        }
    }

    private val _uiState = MutableStateFlow(CreateTransactionUiState())
    val uiState = combine(_prefs, _amountEntered, _uiState) { prefs, amountEntered, uiState ->
        val currencyFormatter = currencyFormatterFactory.getFormatterSymbolOnly(uiState.transactionType)
        uiState.copy(
            amountEntered = amountEntered,
            amount = currencyFormatter.formatCurrencyString(
               amountEntered,
                prefs
            )
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)


    fun handleAction(action: CreateTransactionAction){
        when(action) {
            CreateTransactionAction.CreateTransaction -> {
                println("CREATE TRANSACTION")
                viewModelScope.launch {
                    createTransactionUseCase.createTransaction(
                        transactionType = _uiState.value.transactionType,
                        amountString = _amountEntered.value,
                        recipient = _uiState.value.recipient,
                        description = _uiState.value.description,
                        category = _pickedCategory.value
                    ).let {res ->
                        when(res){
                            is SpendResult.Failure -> {
                                println("FAILED TO CREATE TRANSACTION: $res")

                                onNavAction(res.data)
                            }
                            is SpendResult.Success -> {
                                //proceed with saving
                                val transaction = res.data
                                //saveTransactionUseCase.save(transaction.toDomain())
                                println("SAVED TRANSACTION: $transaction")
                                onNavAction(transaction)
                            }
                        }
                        _amountEntered.update {
                            ""
                        }
                        _uiState.update {
                            CreateTransactionUiState()
                        }
                    }
                }
            }
            is CreateTransactionAction.SaveTransaction -> {
                viewModelScope.launch {
                    saveTransactionUseCase.save(action.transaction)
                }
            }
            CreateTransactionAction.ToggleExpenseIncome -> {
                _uiState.update {
                    it.copy(
                        transactionType = toggleTransactionType()
                    )
                }
            }

            is CreateTransactionAction.EnterAmount -> {
                println("ENTER AMOUNT: ${action.amount}")
               _amountEntered.update {
                   stringToThousandsAndDecimals(action.amount).let {
                       it.first + it.second
                   }
               }
            }
            is CreateTransactionAction.EnterDescription -> {
                _uiState.update {
                    it.copy(
                        description = action.description.take(100)
                    )
                }
            }
            is CreateTransactionAction.EnterRecipient -> {
                _uiState.update {
                    it.copy(
                        recipient = action.recipient
                    )
                }
            }

        }
    }

    fun onCategoryAction(action: CategoryAction){
        when(action) {
            is CategoryAction.SelectCategory -> {
                //todo
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