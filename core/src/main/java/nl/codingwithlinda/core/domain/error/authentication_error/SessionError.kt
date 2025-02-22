package nl.codingwithlinda.core.domain.error.authentication_error

import nl.codingwithlinda.core.domain.error.RootError

sealed interface SessionError: RootError {
    data class LoginFailedError(val error: LoginError) : SessionError
    data object NotLoggedInError: SessionError
    data object NoAccountError: SessionError
    data object SessionLockedError: SessionError
    data object SessionExpiredError: SessionError
    data object WrongPINError: SessionError

}

