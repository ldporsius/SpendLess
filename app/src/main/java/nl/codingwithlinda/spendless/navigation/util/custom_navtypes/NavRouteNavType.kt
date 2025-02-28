package nl.codingwithlinda.spendless.navigation.util.custom_navtypes

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute

object NavRouteNavType {

    val NavRouteType = object : NavType<NavRoute>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): NavRoute? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): NavRoute{
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: NavRoute): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: NavRoute) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}