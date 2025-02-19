package nl.codingwithlinda.core.di

import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.session_manager.SessionManager

interface AppModule {

    val sessionManager: SessionManager

    val accountAccess: DataSourceAccess<Account, Pair<String, String>>
    val accountAccessReadOnly: DataSourceAccessReadOnly<Account, String>
    val preferencesAccess: DataSourceAccess<PreferencesAccount, Long>
    val preferencesAccessForAccount: DataSourceAccessReadOnly<PreferencesAccount, String>
    val transactionsAccess: DataSourceAccessFK<Transaction, Long, String>
}