package nl.codingwithlinda.user_settings.preferences.domain

import kotlinx.coroutines.flow.firstOrNull
import nl.codingwithlinda.core.di.PreferencesAccess
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class SaveUserPreferencesUseCase(
    private val sessionManager: SessionManager,
    private val preferencesAccess: PreferencesAccess
) {

    suspend fun save(preferences: Preferences): SpendResult<Unit, RootError>{
        val accountId = sessionManager.getAccountId().firstOrNull() ?: return SpendResult.Failure(SessionError.NoAccountError)
        val update = PreferencesAccount(
            accountId = accountId,
            preferences = preferences
        )
        preferencesAccess.update(update)

        return SpendResult.Success(Unit)

    }
}