package nl.codingwithlinda.authentication.login.domain

import nl.codingwithlinda.core.domain.error.RootError

sealed interface LoginError: RootError  {
    data object UserNameUnknownError: LoginError
    data object WrongPINError: LoginError
}