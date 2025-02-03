package nl.codingwithlinda.core.fake_data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account

class FakeAccountAccess: DataSourceAccess<Account, Pair<String, String>> {

    val accounts = MutableList<Account>(1){
        Account(
            userName = "lin",
            pin = "12345"
        )
    }
    override suspend fun create(item: Account): Account {
        accounts.add(item)
        return item
    }

    override suspend fun read(id: Pair<String, String>): Account? {
       return accounts.find {
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