package nl.codingwithlinda.user_settings.onboarding.domain

import nl.codingwithlinda.core.data.encryption.AccountCryptor
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class SaveAccountAndPreferencesUseCase(
    private val accountCryptor: AccountCryptor,
    private val accountAccess: DataSourceAccess<Account, Pair<String, String>>,
    private val preferencesAccess: DataSourceAccess<PreferencesAccount, Long>,
    private val sessionManager: SessionManager
) {

    suspend fun save(account: Account, preferences: Preferences){
        accountCryptor.encrypt(account).also {
            accountAccess.create(it)
        }

        preferencesAccess.create(
            PreferencesAccount(
                preferences = preferences,
                accountId = account.id
            )
        )

        sessionManager.setAccountId(account.id)
        sessionManager.startSession()
    }
}