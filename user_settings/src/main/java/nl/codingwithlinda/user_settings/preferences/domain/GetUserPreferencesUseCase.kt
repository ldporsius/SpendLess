package nl.codingwithlinda.user_settings.preferences.domain

import kotlinx.coroutines.flow.firstOrNull
import nl.codingwithlinda.core.di.PreferencesAccess
import nl.codingwithlinda.core.di.PreferencesAccessForAccount
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.error.settings_error.SettingsError
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class GetUserPreferencesUseCase(
    private val sessionManager: SessionManager,
    private val preferencesAccess: PreferencesAccessForAccount
):IGetUserPrefs {

    override suspend fun getPreferencesForLoggedInUser(): SpendResult<PreferencesAccount, RootError>{
        val accountId = sessionManager.getAccountId().firstOrNull() ?: return SpendResult.Failure(SessionError.NoAccountError)

        preferencesAccess.getById(accountId)?.let {
            return SpendResult.Success(it)
        }

        return SpendResult.Failure(SettingsError.PREFERENCES_NOT_FOUND)

    }
}