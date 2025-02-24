package nl.codingwithlinda.dashboard.transactions.transactions_all.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDateGroup
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.toUi

class TransactionsAllViewModel(
    private val currencyFormatterFactory: CurrencyFormatterFactory,
    private val appModule: AppModule
): ViewModel() {

    private val sessionManager  = appModule.sessionManager

    private val preferencesAccess = appModule.preferencesAccessForAccount
    private val transactionsAccess = appModule.transactionsAccess
    private val _transactions = MutableStateFlow<List<TransactionGroupUi>>(emptyList())

    val transactions = _transactions
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _transactions.value)

    private suspend fun transactions() : Flow<List<TransactionGroupUi>> {
        val accountId = sessionManager.getAccountId().firstOrNull() ?: return emptyFlow()

        println("TransactionsAllViewModel has accountId: $accountId")

        val prefs = preferencesAccess.getById(accountId)
        println("TransactionsAllViewModel has prefs: $prefs")

        val transactions = accountId.let{ userId ->
            transactionsAccess.readAllFK(userId)
        }.map {
            if (prefs == null){
                return@map emptyList<TransactionGroupUi>()
            }
            it.groupByDateGroup().toUi(currencyFormatterFactory, prefs.preferences)
            //it.groupByDate().toUi(currencyFormatterFactory, prefs.preferences)
        }
        return transactions
    }

    init {
        viewModelScope.launch {
            transactions().collect{transactions ->
                _transactions.update {
                    transactions
                }
            }
        }
    }

}