package nl.codingwithlinda.spendless.di

import android.app.Application
import nl.codingwithlinda.core.data.session_manager.DataStoreSessionManager
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core_ui.currency.CurrencyFormatter
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterExpense
import nl.codingwithlinda.persistence.data.repository.AccountAccessReadOnly
import nl.codingwithlinda.persistence.data.repository.AccountRepo
import nl.codingwithlinda.persistence.data.repository.PreferencesAccessForAccount
import nl.codingwithlinda.persistence.data.repository.PreferencesRepo
import nl.codingwithlinda.persistence.data.repository.TransactionRepo

class AndroidAppModule(
    private val application: Application
): AppModule {
    override val sessionManager: SessionManager
        get() = DataStoreSessionManager(application)

    override val accountAccess: DataSourceAccess<Account, Pair<String, String>>
        get() = AccountRepo(application).getAccountAccess()

    override val accountAccessReadOnly: DataSourceAccessReadOnly<Account, String>
        = AccountAccessReadOnly(application)

    override val preferencesAccess: DataSourceAccess<PreferencesAccount, Long>
        get() = PreferencesRepo(application).getPreferencesAccess()

    override val preferencesAccessForAccount: DataSourceAccessReadOnly<PreferencesAccount, String>
        get() = PreferencesAccessForAccount(application)

    override val transactionsAccess: DataSourceAccessFK<Transaction, Long, String>
        get() = TransactionRepo(application).access
}