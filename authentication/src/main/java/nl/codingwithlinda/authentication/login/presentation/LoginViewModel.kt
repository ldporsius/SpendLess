package nl.codingwithlinda.authentication.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.authentication.login.domain.usecase.StartSessionUseCase
import nl.codingwithlinda.authentication.login.presentation.error.toUiText
import nl.codingwithlinda.authentication.login.presentation.state.LoginAction
import nl.codingwithlinda.authentication.login.presentation.state.LoginViewState
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core_ui.util.UiText


class LoginViewModel(
    private val loginValidator: LoginValidator,
    private val startSessionUseCase: StartSessionUseCase,
    private val navToDashboard: () -> Unit
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


    private val _loginState = combine(_userNameInput, _pinInput){ name, pin ->
        loginValidator.validateCredentials(name, pin)
    }

    private val _errorChannel = Channel<UiText?>()
    val errorChannel = _errorChannel.receiveAsFlow()

    init {
        _loginState.onEach {res ->
            when(res){
                is SpendResult.Failure -> {
                    _errorChannel.send(res.error.toUiText())
                    _uiState.update {
                        it.copy(
                            isLoginValid = false
                        )
                    }
                }
                is SpendResult.Success -> {
                    _errorChannel.send(null)
                    _uiState.update {
                        it.copy(
                            isLoginValid = res.data != null
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
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

            LoginAction.LoginAttempt -> {
                println("LOGIN VIEW MODEL LOGINATTEMPT EVENT CALLED")
                viewModelScope.launch {
                    _loginState.firstOrNull()?.let {res ->
                        when(res){
                            is SpendResult.Failure -> {
                                _uiState.update {
                                    it.copy(
                                        isLoginValid = false
                                    )
                                }
                            }
                            is SpendResult.Success -> {
                                res.data?.let {account ->
                                    startSessionUseCase.startSession(account)
                                    navToDashboard()
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}