package nl.codingwithlinda.dashboard.core.domain.usecase

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
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

    fun transactionsForLoggedInAccount() =
        sessionManager.getUserId().transform<String?, SpendResult<List<Transaction>, RootError>>{ accountId ->
            if (accountId == null) {
                emit(SpendResult.Failure(SessionError.NoAccountError))
                return@transform
            }
            emitAll(
                transactionsAccess.readAllFK(accountId).map {
                    SpendResult.Success(it)
                }
            )
        }
}