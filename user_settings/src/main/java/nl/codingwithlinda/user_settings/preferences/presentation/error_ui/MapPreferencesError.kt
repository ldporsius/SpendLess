package nl.codingwithlinda.user_settings.preferences.presentation.error_ui

import nl.codingwithlinda.core.domain.error.settings_error.SettingsError
import nl.codingwithlinda.core_ui.util.UiText

fun SettingsError.toUiText(): UiText{
   return when(this){
        SettingsError.PREFERENCES_NOT_FOUND -> UiText.DynamicText("Preferences not found")
    }
}