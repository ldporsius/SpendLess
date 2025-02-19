package nl.codingwithlinda.dashboard.transactions.transactions_all.presentation

import androidx.lifecycle.ViewModel
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.categories.data.CategoryFactory

class TransactionsAllViewModel(
    private val currencyFormatterFactory: CurrencyFormatterFactory,
    private val categoryFactory: CategoryFactory,
    appModule: AppModule
): ViewModel() {

    private val preferencesAccess = appModule.preferencesAccess
    private val transactionsAccess = appModule.transactionsAccess
    private val _transactions = transactionsAccess.readAll()

}