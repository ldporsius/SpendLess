package nl.codingwithlinda.authentication.core.domain.error

import nl.codingwithlinda.authentication.login.domain.LoginError
import nl.codingwithlinda.core.domain.error.RootError

sealed interface SessionError: RootError {

    data object NoAccountError: SessionError
    object SessionLockedError: SessionError
    object SessionExpiredError: SessionError
    data class LoginFailedError(val error: LoginError): SessionError

}

