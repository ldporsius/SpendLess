package nl.codingwithlinda.user_settings.preferences.domain

import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.result.SpendResult

interface IGetUserPrefs {

    suspend fun getPreferencesForLoggedInUser(): SpendResult<PreferencesAccount, RootError>
}