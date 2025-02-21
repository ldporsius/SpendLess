package nl.codingwithlinda.dashboard.core.domain.usecase

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import nl.codingwithlinda.core.di.AccountAccess
import nl.codingwithlinda.core.di.AccountAccessReadOnly
import nl.codingwithlinda.core.di.TransactionsAccess
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class TransactionForAccountUseCase(
    private val transactionsAccess: TransactionsAccess,
    private val sessionManager: SessionManager
) {

    fun transactionsForLoggedInAccount() = flow<SpendResult<List<Transaction>, RootError>>{

        sessionManager.getUserId().map{ accountId ->
            if (accountId == null) {
                emit(SpendResult.Failure(SessionError.NoAccountError))
                return@map
            }
            transactionsAccess.readAllFK(accountId).onEach {
                emit(SpendResult.Success(it))
            }
        }
    }

}