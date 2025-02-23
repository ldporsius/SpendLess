package nl.codingwithlinda.persistence.room.data.mapping

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.test_data.fakePreferences
import nl.codingwithlinda.persistence.room.domain.model.PreferencesEntity

fun PreferencesAccount.toJson(): String{
    return Json.encodeToString(this)
}

fun PreferencesAccount.toEntity(): PreferencesEntity {
    return PreferencesEntity(
        preferencesJson = Json.encodeToString(preferences),
        accountId = accountId
    )
}

fun PreferencesEntity.toDomain(): PreferencesAccount{
    val prefs = Json.decodeFromString<Preferences>(preferencesJson)

    return PreferencesAccount(
        preferences = prefs,
        accountId = accountId
    )
}