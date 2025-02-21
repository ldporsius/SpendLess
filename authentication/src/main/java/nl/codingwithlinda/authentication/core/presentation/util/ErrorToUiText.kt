package nl.codingwithlinda.authentication.core.presentation.util

import nl.codingwithlinda.core.domain.error.authentication_error.AuthenticationError
import nl.codingwithlinda.core.domain.error.authentication_error.PINDiffersError
import nl.codingwithlinda.core.domain.error.authentication_error.UserNameDuplicateError
import nl.codingwithlinda.authentication.validation.UserNameValidator.Companion.MAX_LENGTH
import nl.codingwithlinda.authentication.validation.UserNameValidator.Companion.MIN_LENGTH
import nl.codingwithlinda.core_ui.util.UiText

fun AuthenticationError.toUiText(): UiText = when(this) {
    AuthenticationError.UserNameInvalidCharactersError -> UiText.DynamicText("Username can only contain letters and numbers")
    AuthenticationError.UserNameLongError -> UiText.DynamicText("Username cannot be longer than $MAX_LENGTH characters")
    AuthenticationError.UserNameShortError -> UiText.DynamicText("Username must be at least $MIN_LENGTH characters")
}

fun UserNameDuplicateError.toUiText(): UiText = UiText.DynamicText("Username already exists")

fun PINDiffersError.toUiText(): UiText = UiText.DynamicText("PINs don\'t match. Try again.")