package nl.codingwithlinda.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import nl.codingwithlinda.core.domain.model.Account

object CustomNavType {

    val AccountType = object : NavType<Account>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Account? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Account{
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Account): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Account) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}