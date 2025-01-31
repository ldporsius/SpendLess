package nl.codingwithlinda.authentication.registration.presentation.state

sealed interface RegisterAction {
    data class NameInput(val name: String) : RegisterAction
}