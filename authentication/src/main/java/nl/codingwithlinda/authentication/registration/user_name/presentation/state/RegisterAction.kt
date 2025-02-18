package nl.codingwithlinda.authentication.registration.user_name.presentation.state

sealed interface RegisterAction {
    data class NameInput(val name: String) : RegisterAction
}