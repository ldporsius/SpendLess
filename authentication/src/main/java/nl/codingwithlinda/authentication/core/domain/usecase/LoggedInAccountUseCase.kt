package nl.codingwithlinda.authentication.core.domain.usecase

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import nl.codingwithlinda.core.di.AccountAccessReadOnly
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class LoggedInAccountUseCase(
    private val accountAccess: AccountAccessReadOnly,
    private val sessionManager: SessionManager
) {

    val x = sessionManager.getUserId()
        .transform<String?, String> {accountId ->

            emit(accountId ?: throw Exception("No account id"))

    }

    val y = sessionManager.getUserId()
        .transform<String?, Account?> {accountId ->

           accountId?.let { id ->
               accountAccess.read(id).also {
                   emitAll(it)
               }
           }
        }
   val loggedInAccount= flow<SpendResult<Account, RootError>>{

        sessionManager.getUserId().map{ accountId ->
            if (accountId == null) {
                emit(SpendResult.Failure(SessionError.NoAccountError))

            }
            accountId?.let {
                accountAccess.read(accountId).onEach { account ->
                    if (account == null) {
                        emit(SpendResult.Failure(SessionError.NoAccountError))
                        return@onEach
                    } else
                        emit(SpendResult.Success(account))
                }
            }
        }
    }

}