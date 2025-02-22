package nl.codingwithlinda.authentication.core.domain.usecase

import kotlinx.coroutines.flow.transform
import nl.codingwithlinda.core.di.AccountAccessReadOnly
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class LoggedInAccountUseCase(
    private val accountAccess: AccountAccessReadOnly,
    sessionManager: SessionManager
) {

   val loggedInAccount=

        sessionManager.getUserId().transform<String?, SpendResult<Account?, SessionError>>{ accountId ->
            if (accountId == null) {
                emit(SpendResult.Failure(SessionError.NotLoggedInError))

            }
            accountId?.let {
                accountAccess.getById(it).let { account ->
                    if (account == null) {
                        emit(SpendResult.Failure(SessionError.NoAccountError))

                    } else
                    emit(SpendResult.Success(account))
                }
            }
        }

}