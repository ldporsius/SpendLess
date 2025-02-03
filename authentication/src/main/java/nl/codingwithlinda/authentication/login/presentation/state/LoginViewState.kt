package nl.codingwithlinda.authentication.login.presentation.state

data class LoginViewState(
    val userNameInput: String = "",
    val pinInput: String = "",
    val isLoginValid: Boolean = false,
)
