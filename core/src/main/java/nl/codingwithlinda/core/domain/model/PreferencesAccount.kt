package nl.codingwithlinda.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PreferencesAccount(
    val preferences: Preferences,
    val accountId: String
)
