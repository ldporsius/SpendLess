package nl.codingwithlinda.spendless.navigation.util.custom_navtypes

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import nl.codingwithlinda.core.data.dto.TransactionDto

object TransactionDtoNavType {

    val TransactionDtoType = object : NavType<TransactionDto>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): TransactionDto? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): TransactionDto {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: TransactionDto): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: TransactionDto) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}