package nl.codingwithlinda.persistence.data.mapping

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.persistence.model.PreferencesEntity

fun Preferences.toJson(): String{
    return Json.encodeToString(this)
}

fun Preferences.toEntity(): PreferencesEntity {
    return PreferencesEntity(
        preferencesJson = toJson()
    )
}

fun PreferencesEntity.toDomain(): Preferences{
    return Json.decodeFromString(preferencesJson)
}