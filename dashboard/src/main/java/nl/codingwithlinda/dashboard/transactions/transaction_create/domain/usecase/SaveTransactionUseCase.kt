package nl.codingwithlinda.dashboard.transactions.transaction_create.domain.usecase

import nl.codingwithlinda.core.di.TransactionsAccess
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.result.SpendResult

class SaveTransactionUseCase(
    private val transactionsAccess: TransactionsAccess,
) {

    suspend fun save(transaction: Transaction): SpendResult<Transaction, RootError> {
        transactionsAccess.create(transaction)
        return SpendResult.Success(transaction)

    }
}