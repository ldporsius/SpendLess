package nl.codingwithlinda.core.domain.error.login

import nl.codingwithlinda.core.domain.error.RootError

sealed interface LoginError: RootError  {
    data object UserNameUnknownError: LoginError
    data object WrongPINError: LoginError


}