package nl.codingwithlinda.dashboard.transactions.transactions_all.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.test_data.fakePreferencesAccount
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.toUi

class TransactionsAllViewModel(
    private val currencyFormatterFactory: CurrencyFormatterFactory,
    private val appModule: AppModule
): ViewModel() {

    private val sessionManager  = appModule.sessionManager

    private val preferencesAccess = appModule.preferencesAccessForAccount
    private val transactionsAccess = appModule.transactionsAccess
    private val _transactions = MutableStateFlow<List<TransactionGroupUi>>(emptyList())

    val transactions = _transactions.map {
        it
    }.onStart {
        withContext(viewModelScope.coroutineContext){
            transactions().collect{
                _transactions.value = it
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    suspend fun transactions() : Flow<List<TransactionGroupUi>> {
        val accountId = sessionManager.getUserId().firstOrNull() ?: return emptyFlow()

        println("TransactionsAllViewModel has accountId: $accountId")

        val prefs = preferencesAccess.getById(accountId) ?: fakePreferencesAccount(accountId)
        println("TransactionsAllViewModel has prefs: $prefs")

       val transactions = accountId.let{ userId ->
           transactionsAccess.readAllFK(userId)
       }

        return transactions.map {
            it.groupByDate().toUi(currencyFormatterFactory, prefs.preferences)
        }
    }

}