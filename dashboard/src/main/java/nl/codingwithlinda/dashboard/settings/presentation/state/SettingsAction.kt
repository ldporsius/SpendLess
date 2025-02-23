package nl.codingwithlinda.dashboard.settings.presentation.state

sealed interface SettingsAction {
    data object NavigateToPreferences : SettingsAction
    data object NavigateToSecurity : SettingsAction
    data object NavigateToLogout : SettingsAction

}