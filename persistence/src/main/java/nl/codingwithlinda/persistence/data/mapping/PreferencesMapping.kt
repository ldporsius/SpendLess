package nl.codingwithlinda.persistence.data.mapping

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.persistence.model.PreferencesEntity

fun PreferencesAccount.toJson(): String{
    return Json.encodeToString(this)
}

fun PreferencesAccount.toEntity(

): PreferencesEntity {
    return PreferencesEntity(
        preferencesJson = toJson(),
        accountId = accountId
    )
}

fun PreferencesEntity.toDomain(): PreferencesAccount{
    return Json.decodeFromString(preferencesJson)
}