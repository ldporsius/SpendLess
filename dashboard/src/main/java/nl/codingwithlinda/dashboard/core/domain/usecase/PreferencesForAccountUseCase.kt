package nl.codingwithlinda.dashboard.core.domain.usecase

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import nl.codingwithlinda.core.di.AccountAccess
import nl.codingwithlinda.core.di.AccountAccessReadOnly
import nl.codingwithlinda.core.di.PreferencesAccessForAccount
import nl.codingwithlinda.core.di.TransactionsAccess
import nl.codingwithlinda.core.domain.error.NoDataError
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class PreferencesForAccountUseCase(
    private val preferencesAccess: PreferencesAccessForAccount,
    private val sessionManager: SessionManager
) {

    fun preferencesForLoggedInAccount() =
        sessionManager.getUserId().transform<String?,SpendResult<PreferencesAccount, RootError>>{ accountId ->
            if (accountId == null) {
                emit(SpendResult.Failure(SessionError.NoAccountError))
                return@transform
            }
            preferencesAccess.getById(accountId).let {prefs ->
                if (prefs == null){
                    emit(SpendResult.Failure(NoDataError))
                }
                else
                    emit(SpendResult.Success(prefs))
            }
        }

}