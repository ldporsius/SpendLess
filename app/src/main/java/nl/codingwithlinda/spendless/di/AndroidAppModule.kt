package nl.codingwithlinda.spendless.di

import android.app.Application
import nl.codingwithlinda.core.data.session_manager.DataStoreSessionManager
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.CurrencyFormatter
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.core.presentation.util.CurrencyFormatterImpl
import nl.codingwithlinda.persistence.data.repository.AccountRepo
import nl.codingwithlinda.persistence.data.repository.PreferencesRepo

class AndroidAppModule(
    private val application: Application
): AppModule {
    override val sessionManager: SessionManager
        get() = DataStoreSessionManager(application)

    override val currencyFormatter: CurrencyFormatter
        get() = CurrencyFormatterImpl(application)

    override val accountAccess: DataSourceAccess<Account, Pair<String, String>>
        get() = AccountRepo(application).getAccountAccess()
    override val preferencesAccess: DataSourceAccess<Preferences, Long>
        get() = PreferencesRepo(application).getPreferencesAccess()
}