package nl.codingwithlinda.dashboard.transactions.transaction_create.domain.usecase

import nl.codingwithlinda.core.di.TransactionsAccess
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.core.domain.session_manager.ESessionState
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class CreateTransactionUseCase(
    private val transactionsAccess: TransactionsAccess,
    private val sessionManager: SessionManager,
    private val authenticationManager: AuthenticationManager
) {

    suspend fun createTransaction(

    ): SpendResult<Unit, SessionError> {

        authenticationManager.handleEvent().let {
            return when(it){
                ESessionState.OK -> {
                    //SpendResult.Success(Unit)
                    //testing, to be removed
                    SpendResult.Failure(SessionError.SessionExpiredError)
                }
                ESessionState.LOGGED_OUT -> {
                    SpendResult.Failure(SessionError.NotLoggedInError)
                }
                ESessionState.SESSION_EXPRIRED -> {
                    SpendResult.Failure(SessionError.SessionExpiredError)
                }
            }
        }

    }
}