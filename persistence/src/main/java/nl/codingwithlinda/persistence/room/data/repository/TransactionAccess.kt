package nl.codingwithlinda.persistence.room.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.persistence.room.data.mapping.toDomain
import nl.codingwithlinda.persistence.room.data.mapping.toEntity
import nl.codingwithlinda.persistence.room.dao.TransactionDao

class TransactionAccess(
    private val transactionDao: TransactionDao
): DataSourceAccessFK<Transaction, Long, String> {
    override suspend fun create(item: Transaction): Transaction {
        transactionDao.insertTransaction(item.toEntity())
        return item
    }

    override suspend fun update(item: Transaction): Transaction {
       transactionDao.insertTransaction(item.toEntity())
        return item
    }

    override fun readAll(): Flow<List<Transaction>> {
        return transactionDao.getTransactionsForAccount("").map {
            it.map {
                it.toDomain()
            }
        }
    }

    override fun readAllFK(foreignKey: String): Flow<List<Transaction>> {
        return transactionDao.getTransactionsForAccount(foreignKey).map {
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
       return transactionDao.getTransactionById(id)?.toDomain()
    }
}