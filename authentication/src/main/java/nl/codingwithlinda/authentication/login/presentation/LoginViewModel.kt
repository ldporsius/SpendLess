package nl.codingwithlinda.authentication.login.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.codingwithlinda.authentication.login.presentation.state.LoginAction
import nl.codingwithlinda.authentication.login.presentation.state.LoginViewState

class LoginViewModel(

): ViewModel() {

    private val _uiState = MutableStateFlow(LoginViewState())
    val uiState = _uiState.asStateFlow()


    fun handleAction(action: LoginAction) {
        when(action){
            is LoginAction.UserNameInputAction -> {
                _uiState.value = _uiState.value.copy(
                    userNameInput = action.userName
                )
            }
            is LoginAction.PINInputAction -> {
                _uiState.value = _uiState.value.copy(
                    pinInput = action.pin
                )
            }

        }
    }
}