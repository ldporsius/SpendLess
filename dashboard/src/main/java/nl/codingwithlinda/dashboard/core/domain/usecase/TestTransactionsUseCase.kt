package nl.codingwithlinda.dashboard.core.domain.usecase

import kotlinx.coroutines.flow.firstOrNull
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.core.test_data.test_data_generators.fakeTransactions

class TestTransactionsUseCase(
    private val transactionsAccess: DataSourceAccessFK<Transaction, Long, String>,
    private val sessionManager: SessionManager
) {

    suspend fun insertFakeTransactions(){
        transactionsAccess.delete(-1)

        sessionManager.getAccountId().firstOrNull()?.let {
            fakeTransactions(it).onEach {
                transactionsAccess.create(it)
            }
        }
    }
}