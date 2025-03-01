package nl.codingwithlinda.core.test_data.local_cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.PreferencesAccount

class FakePreferencesForAccountAccess(
    private val preferencesAccounts: List<PreferencesAccount>
): DataSourceAccessReadOnly<PreferencesAccount, String> {
    override fun read(id: String): Flow<PreferencesAccount?> {
        return flowOf(
            preferencesAccounts.find {
                it.accountId == id
            }
        )
    }

    override suspend fun getById(id: String): PreferencesAccount? {
        return preferencesAccounts.find {
            it.accountId == id
        }
    }
}