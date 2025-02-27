package nl.codingwithlinda.spendless.navigation.core.destinations

import kotlinx.serialization.Serializable

@Serializable
data object UserSettingsGraph : NavRoute

@Serializable
data object UserSettingsRootNav : NavRoute

@Serializable
data object UserSettingsPreferencesNav : NavRoute

@Serializable
data object UserSettingsSecurityNav : NavRoute