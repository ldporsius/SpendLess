package nl.codingwithlinda.user_settings.main.presentation.state

sealed interface SettingsAction {
    data object NavigateToPreferences : SettingsAction
    data object NavigateToSecurity : SettingsAction
    data object NavigateToLogout : SettingsAction

}