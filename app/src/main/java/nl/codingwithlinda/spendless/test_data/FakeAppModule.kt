package nl.codingwithlinda.spendless.test_data

import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.core.test_data.local_cache.FakeAccountAccess
import nl.codingwithlinda.core.test_data.local_cache.FakeAccountAccessReadOnly
import nl.codingwithlinda.core.test_data.FakeCryptor
import nl.codingwithlinda.core.test_data.local_cache.FakePreferencesAccess
import nl.codingwithlinda.core.test_data.local_cache.FakePreferencesForAccountAccess
import nl.codingwithlinda.core.test_data.FakeSessionManager
import nl.codingwithlinda.core.test_data.local_cache.FakeTransactionAccess
import nl.codingwithlinda.spendless.data.authentication_manager.AppAuthenticationManager

class FakeAppModule(
    private val accounts: List<Account>,
    private val preferencesAccounts: List<PreferencesAccount>,
    private val transactions: List<Transaction>
): AppModule {
    override val accountAccess: DataSourceAccess<Account, Pair<String, String>>
        get() = FakeAccountAccess(
            accounts = accounts.toMutableList()
        )
    override val accountAccessReadOnly: DataSourceAccessReadOnly<Account, String>
        get() = FakeAccountAccessReadOnly(accounts)

    override val preferencesAccess: DataSourceAccess<PreferencesAccount, String>
        get() = FakePreferencesAccess(preferencesAccounts.toMutableList())

    override val preferencesAccessForAccount: DataSourceAccessReadOnly<PreferencesAccount, String>
        get() = FakePreferencesForAccountAccess(preferencesAccounts)

    override val transactionsAccess: DataSourceAccessFK<Transaction, Long, String>
        get() = FakeTransactionAccess(
            transactions = transactions.toMutableList()
        )

    override val sessionManager: SessionManager
        get() = FakeSessionManager()

    override val authenticationManager: AuthenticationManager
        get() = AppAuthenticationManager(
            sessionManager = sessionManager,
            loginValidator = LoginValidator(
                accountAccess = accountAccess,
                accountCryptor = FakeCryptor()
            ),
            accountAccess = accountAccessReadOnly
        )

}