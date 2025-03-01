package nl.codingwithlinda.core.test_data.local_cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.model.Transaction

class FakeTransactionAccess(
    private val transactions: MutableList<Transaction>
): DataSourceAccessFK<Transaction, Long, String> {
    override suspend fun create(item: Transaction): Transaction {
        transactions.add(item)
        return item
    }

    override suspend fun update(item: Transaction): Transaction {
        transactions.remove(item)
        transactions.add(item)
        return item
    }

    override fun readAll(): Flow<List<Transaction>> {
        return flowOf(transactions)
    }

    override fun readAllFK(foreignKey: String): Flow<List<Transaction>> {
        return flowOf(transactions.filter { it.accountId == foreignKey })
    }

    override suspend fun delete(id: Long): Boolean {
        return false
    }

    override suspend fun read(id: Long): Transaction? {
        return transactions.find { it.id == id }
    }
}