package nl.codingwithlinda.authentication.core.presentation.error

import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.session_manager.SessionManager.Companion.LOCKED_OUT_DURATION
import nl.codingwithlinda.core_ui.util.UiText

fun SessionError.toUiText(): UiText{
    return when(this){
        is SessionError.LoginFailedError -> UiText.DynamicText("Invalid username or password")
        SessionError.NoAccountError -> UiText.DynamicText("No account found")
        SessionError.SessionExpiredError -> UiText.DynamicText("Session expired")
        SessionError.SessionLockedError -> UiText.DynamicText("Session locked. Please try again after ${LOCKED_OUT_DURATION / 1000} seconds")
        SessionError.WrongPINError -> UiText.DynamicText("Wrong PIN")
    }
}