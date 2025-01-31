package nl.codingwithlinda.core.domain.error.authentication

import nl.codingwithlinda.core.domain.error.RootError

sealed interface AuthenticationError: RootError  {
    object UserNameShortError: AuthenticationError
    object UserNameLongError: AuthenticationError
    object UserNameInvalidCharactersError: AuthenticationError

}