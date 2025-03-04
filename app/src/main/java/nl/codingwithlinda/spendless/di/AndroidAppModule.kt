package nl.codingwithlinda.spendless.di

import android.app.Application
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.core.data.encryption.AccountCryptor
import nl.codingwithlinda.spendless.data.session_manager.DataStoreSessionManager
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.persistence.room.data.repository.AccountAccessReadOnly
import nl.codingwithlinda.persistence.room.data.repository.AccountRepo
import nl.codingwithlinda.persistence.room.data.repository.PreferencesAccessForAccount
import nl.codingwithlinda.persistence.room.data.repository.PreferencesRepo
import nl.codingwithlinda.persistence.room.data.repository.TransactionRepo
import nl.codingwithlinda.spendless.data.authentication_manager.AppAuthenticationManager

class AndroidAppModule(
    private val application: Application
): AppModule {

    override val accountAccess: DataSourceAccess<Account, Pair<String, String>>
        get() = AccountRepo(application).getAccountAccess()

    override val accountAccessReadOnly: DataSourceAccessReadOnly<Account, String>
        = AccountAccessReadOnly(application)

    override val preferencesAccess: DataSourceAccess<PreferencesAccount,String>
        get() = PreferencesRepo(application).getPreferencesAccess()

    override val preferencesAccessForAccount: DataSourceAccessReadOnly<PreferencesAccount, String>
        get() = PreferencesAccessForAccount(application)

    override val transactionsAccess: DataSourceAccessFK<Transaction, Long, String>
        get() = TransactionRepo(application).access

    override val sessionManager: SessionManager
            = DataStoreSessionManager(
        context = application,
    )

    override val authenticationManager: AuthenticationManager
            = AppAuthenticationManager(
        sessionManager = sessionManager,
        loginValidator = LoginValidator(
            accountAccess = accountAccess,
            accountCryptor = AccountCryptor()
        ),
        accountAccess = accountAccessReadOnly
    )
}