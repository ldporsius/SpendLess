package nl.codingwithlinda.authentication.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.authentication.login.presentation.state.LoginAction
import nl.codingwithlinda.authentication.login.presentation.state.LoginViewState


@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModel(
 private val loginValidator: LoginValidator
): ViewModel() {


    private val _userNameInput = MutableStateFlow("")
    private val _pinInput = MutableStateFlow("")

    private val _uiState = MutableStateFlow(LoginViewState())

    val uiState = combine(_userNameInput, _pinInput, _uiState){ name, pin, state ->
        state.copy(
            userNameInput = name,
            pinInput = pin,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)


    fun handleAction(action: LoginAction) {
        when(action){
            is LoginAction.UserNameInputAction -> {
               _userNameInput.update {
                   action.userName
               }
            }
            is LoginAction.PINInputAction -> {
               _pinInput.update {
                   action.pin
               }
            }

        }
    }
}