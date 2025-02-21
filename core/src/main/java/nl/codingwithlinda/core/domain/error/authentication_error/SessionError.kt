package nl.codingwithlinda.core.domain.error.authentication_error

import nl.codingwithlinda.core.domain.error.RootError

sealed interface SessionError: RootError {
    class LoginFailedError(error: LoginError) : SessionError {

    }

    data object NoAccountError: SessionError
    object SessionLockedError: SessionError
    object SessionExpiredError: SessionError
    object WrongPINError: SessionError

}

