package nl.codingwithlinda.dashboard.transactions.transaction_create.domain.usecase

import kotlinx.coroutines.flow.firstOrNull
import nl.codingwithlinda.core.data.dto.TransactionDto
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.core.domain.session_manager.ESessionState
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import java.util.UUID

class CreateTransactionUseCase(
    private val sessionManager: SessionManager,
    private val authenticationManager: AuthenticationManager
) {

    suspend fun createTransaction(
        transactionType: TransactionType,
        amountString: String,
        recipient: String,
        description: String,
        category: ExpenseCategory
    ): SpendResult<TransactionDto, SessionError> {
        val loggedInAccountId = sessionManager.getAccountId().firstOrNull() ?: return SpendResult.Failure(
            error =
            SessionError.NotLoggedInError)

        val amountSigned = if (transactionType == TransactionType.EXPENSE) {
            "-" + amountString
        }else amountString

        val overrideCategory = if (transactionType == TransactionType.INCOME) {
            ExpenseCategory.INCOME
        } else category

        val transaction = TransactionDto(
            id = 0, //is this right?
            amount = amountSigned,
            timestamp = System.currentTimeMillis(),
            title = recipient,
            description = description,
            category = overrideCategory.identifier,
            accountId = loggedInAccountId,
        )

        return SpendResult.Success(transaction)

    }
}