package nl.codingwithlinda.core.test_data.local_cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.PreferencesAccount

class FakePreferencesAccess(
    private val preferencesAccounts: MutableList<PreferencesAccount>
): DataSourceAccess<PreferencesAccount, String> {
    override suspend fun create(item: PreferencesAccount): PreferencesAccount {
        preferencesAccounts.add(item)
        return item
    }

    override suspend fun read(id: String): PreferencesAccount? {
       return null
    }

    override suspend fun update(item: PreferencesAccount): PreferencesAccount {
        preferencesAccounts.remove(item)
        preferencesAccounts.add(item)
        return item
    }

    override suspend fun delete(id: String): Boolean {
       return false
    }

    override fun readAll(): Flow<List<PreferencesAccount>> {
        return flowOf(preferencesAccounts)
    }


}