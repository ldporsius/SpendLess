package nl.codingwithlinda.persistence.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.persistence.data.mapping.toDomain
import nl.codingwithlinda.persistence.data.mapping.toEntity
import nl.codingwithlinda.persistence.room.dao.TransactionDao

class TransactionAccess(
    private val transactionDao: TransactionDao
): DataSourceAccess<Transaction, Long> {
    override suspend fun create(item: Transaction): Transaction {
        transactionDao.insertTransaction(item.toEntity())
        return item
    }

    override suspend fun update(item: Transaction): Transaction {
       transactionDao.insertTransaction(item.toEntity())
        return item
    }

    override fun readAll(): Flow<List<Transaction>> {
        return transactionDao.getTransactions().map {
            it.map {
                it.toDomain()
            }
        }
    }

    override suspend fun delete(id: Long): Boolean {
        if(id == -1L) transactionDao.deleteAllTransactions()
        transactionDao.deleteTransaction(id)
        return true
    }

    override suspend fun read(id: Long): Transaction? {
        return transactionDao.getTransactions().firstOrNull()?.firstOrNull {
            it.id == id
        }?.toDomain()
    }
}