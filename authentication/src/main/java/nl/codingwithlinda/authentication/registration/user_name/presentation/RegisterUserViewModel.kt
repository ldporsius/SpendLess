package nl.codingwithlinda.authentication.registration.user_name.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.core.presentation.util.toUiText
import nl.codingwithlinda.authentication.registration.user_name.presentation.state.RegisterAction
import nl.codingwithlinda.authentication.registration.user_name.presentation.state.RegisterUserViewState
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.validation.UserNameValidator

class RegisterUserViewModel(
    private val userNameValidator: UserNameValidator
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUserViewState())
    val uiState = _uiState.asStateFlow()

    fun handleAction(action: RegisterAction) {
        when(action) {
            is RegisterAction.NameInput -> {
                _uiState.value = _uiState.value.copy(
                    userNameInput = action.name,
                    noDataError = null
                )

                val inputResult = userNameValidator.isUserNameInputValid(action.name)
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

                viewModelScope.launch {
                    val isUniqueResult = userNameValidator.isUserNameUnique(action.name)
                    when (isUniqueResult) {
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
}