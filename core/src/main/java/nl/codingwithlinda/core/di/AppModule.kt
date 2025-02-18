package nl.codingwithlinda.core.di

import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.session_manager.SessionManager

interface AppModule {

    val sessionManager: SessionManager

    val accountAccess: DataSourceAccess<Account, Pair<String, String>>
    val preferencesAccess: DataSourceAccess<Preferences, Long>
    val transactionsAccess: DataSourceAccess<Transaction, Long>
}