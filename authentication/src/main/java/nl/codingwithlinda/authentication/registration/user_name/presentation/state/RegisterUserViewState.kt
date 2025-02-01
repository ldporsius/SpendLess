package nl.codingwithlinda.authentication.registration.user_name.presentation.state

import nl.codingwithlinda.core.domain.error.NoDataError
import nl.codingwithlinda.core.presentation.util.UiText

data class RegisterUserViewState(
    val userNameInput: String = "",
    val inputNameError: UiText? = null,
    val userNameDuplicateError: UiText? = null,
    val noDataError: NoDataError? = NoDataError
){
    val isUserNameValid: Boolean = inputNameError == null && noDataError == null && userNameDuplicateError == null
}
