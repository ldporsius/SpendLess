package nl.codingwithlinda.dashboard.core.domain.usecase

import kotlinx.coroutines.flow.transform
import nl.codingwithlinda.core.di.PreferencesAccessForAccount
import nl.codingwithlinda.core.domain.error.NoDataError
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class PreferencesForAccountUseCase(
    private val preferencesAccess: PreferencesAccessForAccount,
    private val sessionManager: SessionManager
) {

    fun preferencesForLoggedInAccount() =
        sessionManager.getAccountId().transform<String?,SpendResult<PreferencesAccount, RootError>>{ accountId ->
            if (accountId == null) {
                emit(SpendResult.Failure(SessionError.NoAccountError))
                return@transform
            }
            preferencesAccess.getById(accountId).let {prefs ->
                if (prefs == null){
                    println("PREFERENCES FOR ACCOUNT USE CASE: No preferences found for account $accountId")
                    emit(SpendResult.Failure(NoDataError))
                }
                else{
                    println("PREFERENCES FOR ACCOUNT USE CASE: Found preferences for account $accountId")
                    println("PREFERENCES FOR ACCOUNT USE CASE: $prefs")
                    emit(SpendResult.Success(prefs))

                }

            }
        }

}