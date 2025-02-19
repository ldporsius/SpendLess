package nl.codingwithlinda.authentication.onboarding.domain

import nl.codingwithlinda.core.data.AccountFactory
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount

class SaveAccountAndPreferencesUseCase(
    private val accountFactory: AccountFactory,
    private val accountAccess: DataSourceAccess<Account, Pair<String, String>>,
    private val preferencesAccess: DataSourceAccess<PreferencesAccount, Long>,
) {

    suspend fun save(account: Account, preferences: Preferences){
        accountFactory.encrypt(account).also {
            accountAccess.create(it)
        }

        preferencesAccess.create(
            PreferencesAccount(
                preferences = preferences,
                accountId = account.id
            )
        )
    }
}