package nl.codingwithlinda.core.fake_data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account

class FakeAccountAccess: DataSourceAccess<Account, Pair<String, String>> {
    override suspend fun create(item: Account): Account {
        return item
    }

    override suspend fun read(id: Pair<String, String>): Account? {
       return null
    }

    override suspend fun update(item: Account): Account {
        return item
    }

    override suspend fun delete(id: Pair<String, String>): Boolean {
        return true
    }

    override fun readAll(): Flow<List<Account>> {
        return flowOf(emptyList())
    }

}