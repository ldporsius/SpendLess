package nl.codingwithlinda.core.test_data.local_cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account

class FakeAccountAccess(
    private val accounts: MutableList<Account>
): DataSourceAccess<Account, Pair<String, String>> {

    override suspend fun create(item: Account): Account {
        accounts.add(item)
        return item
    }

    override suspend fun read(id: Pair<String, String>): Account? {
       return if (id.second.isEmpty()) return accounts.find {
            it.userName == id.first
        }
       else accounts.find {
           it.userName == id.first && it.pin == id.second
       }
    }

    override suspend fun update(item: Account): Account {
        accounts.removeIf {
            it.userName == item.userName
        }
        accounts.add(item)
        return item
    }

    override suspend fun delete(id: Pair<String, String>): Boolean {
        accounts.removeIf {
            it.userName == id.first
        }
        return true
    }

    override fun readAll(): Flow<List<Account>> {
        return flow(
        ){
            emit(accounts)
        }
    }

}