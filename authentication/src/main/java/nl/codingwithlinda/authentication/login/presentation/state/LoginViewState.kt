package nl.codingwithlinda.authentication.login.presentation.state

data class LoginViewState(
    val userNameInput: String = "",
    val pinInput: String = "",
    val isUserNameValid: Boolean = false,
    val isPinValid: Boolean = false
){
    fun isLoginButtonEnabled(): Boolean
        = isUserNameValid && isPinValid
}
