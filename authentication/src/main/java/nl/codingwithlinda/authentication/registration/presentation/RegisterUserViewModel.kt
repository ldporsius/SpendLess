package nl.codingwithlinda.authentication.registration.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.codingwithlinda.authentication.core.presentation.util.toUiText
import nl.codingwithlinda.authentication.registration.presentation.state.RegisterAction
import nl.codingwithlinda.authentication.registration.presentation.state.RegisterUserViewState
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.validation.UserNameValidator

class RegisterUserViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUserViewState())
    val uiState = _uiState.asStateFlow()

    fun handleAction(action: RegisterAction) {
        when(action) {
            is RegisterAction.NameInput -> {
                _uiState.value = _uiState.value.copy(
                    userNameInput = action.name,
                    noDataError = null
                )

                val inputResult = UserNameValidator.isUserNameInputValid(action.name)
                when(inputResult){
                    is SpendResult.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            inputNameError = inputResult.error.toUiText()
                        )
                    }
                    is SpendResult.Success -> {
                        _uiState.value = _uiState.value.copy(
                            inputNameError = null
                        )
                    }
                }

                val isUniqueResult = UserNameValidator.isUserNameUnique(action.name)
                when(isUniqueResult){
                    is SpendResult.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            userNameDuplicateError = isUniqueResult.error.toUiText()
                        )
                    }
                    is SpendResult.Success -> {
                        _uiState.value = _uiState.value.copy(
                            userNameDuplicateError = null
                        )
                    }
                }

            }
        }
    }
}