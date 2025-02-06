package nl.codingwithlinda.authentication.login.presentation.error

import nl.codingwithlinda.authentication.login.domain.LoginError
import nl.codingwithlinda.core_ui.util.UiText

fun LoginError.toUiText(): UiText{
    return when(this){
        LoginError.UserNameUnknownError -> UiText.DynamicText("There is no user with this name")
        LoginError.WrongPINError -> UiText.DynamicText("Username and password do not match")
    }
}