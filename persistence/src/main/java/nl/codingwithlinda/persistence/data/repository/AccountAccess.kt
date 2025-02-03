package nl.codingwithlinda.persistence.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.persistence.data.mapping.toDomain
import nl.codingwithlinda.persistence.data.mapping.toEntity
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.persistence.room.dao.AccountDao

class AccountAccess(
    private val accountDao: AccountDao
): DataSourceAccess<Account, Pair<String, String>> {
    override suspend fun create(item: Account): Account {
        accountDao.createAccount(item.toEntity())
        return item
    }

    override suspend fun read(id:Pair<String,String>): Account? {
        if (id.second.isBlank()) return accountDao.getAccountForUser(id.first)?.toDomain()
        id.second.let {pin ->
            return accountDao.getAccountForUserAndPIN(id.first, pin)?.toDomain()
        }
    }

    override suspend fun update(item: Account): Account {
        accountDao.createAccount(item.toEntity())
        return item
    }

    override suspend fun delete(id: Pair<String,String>): Boolean {
        return false
    }

    override fun readAll(): Flow<List<Account>> {
        return accountDao.readAccounts().map {
            it.map { accountEntity ->
                accountEntity.toDomain()
            }
        }
    }
}