package nl.codingwithlinda.authentication.login.presentation.state

sealed interface LoginAction {
    data class UserNameInputAction(val userName: String): LoginAction
    data class PINInputAction(val pin: String): LoginAction
}