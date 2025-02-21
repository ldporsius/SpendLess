package nl.codingwithlinda.core.domain.error.authentication_error

import nl.codingwithlinda.core.domain.error.RootError

sealed interface LoginError: RootError  {
    data object UserNameUnknownError: LoginError
    data object WrongPINError: LoginError
}