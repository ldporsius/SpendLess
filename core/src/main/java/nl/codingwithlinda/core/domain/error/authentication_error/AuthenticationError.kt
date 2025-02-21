package nl.codingwithlinda.core.domain.error.authentication_error

import nl.codingwithlinda.core.domain.error.RootError

sealed interface AuthenticationError: RootError  {
    data object UserNameShortError: AuthenticationError
    data object UserNameLongError: AuthenticationError
    data object UserNameInvalidCharactersError: AuthenticationError

}