package nl.codingwithlinda.core.test_data.local_cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account

class FakeAccountAccessReadOnly(
    private val accounts: List<Account>
): DataSourceAccessReadOnly<Account, String> {

    override fun read(id: String): Flow<Account?> {
        return flowOf(accounts.find { it.id == id })
    }

    override suspend fun getById(id: String): Account? {
        return accounts.find { it.id == id }
    }
}