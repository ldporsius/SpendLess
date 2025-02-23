package nl.codingwithlinda.spendless.navigation.user_settings

import kotlinx.serialization.Serializable
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute

@Serializable
data object UserSettingsGraph : NavRoute

@Serializable
data object UserSettingsRootNav : NavRoute

@Serializable
data object UserSettingsPreferencesNav : NavRoute

@Serializable
data object UserSettingsSecurityNav : NavRoute