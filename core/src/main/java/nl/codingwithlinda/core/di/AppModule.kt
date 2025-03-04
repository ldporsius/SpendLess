package nl.codingwithlinda.core.di

import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.core.domain.session_manager.SessionManager


typealias AccountAccess = DataSourceAccess<Account, Pair<String, String>>
typealias AccountAccessReadOnly = DataSourceAccessReadOnly<Account, String>
typealias PreferencesAccess = DataSourceAccess<PreferencesAccount, String>
typealias PreferencesAccessForAccount = DataSourceAccessReadOnly<PreferencesAccount, String>
typealias TransactionsAccess = DataSourceAccessFK<Transaction, Long, String>

interface AppModule {

    val sessionManager: SessionManager
    val authenticationManager: AuthenticationManager

    val accountAccess: DataSourceAccess<Account, Pair<String, String>>
    val accountAccessReadOnly: DataSourceAccessReadOnly<Account, String>
    val preferencesAccess: DataSourceAccess<PreferencesAccount, String>
    val preferencesAccessForAccount: DataSourceAccessReadOnly<PreferencesAccount, String>
    val transactionsAccess: DataSourceAccessFK<Transaction, Long, String>
}