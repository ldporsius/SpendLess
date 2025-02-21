package nl.codingwithlinda.authentication.pin_prompt.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.core.domain.usecase.LoggedInAccountUseCase
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.authentication.pin_prompt.presentation.state.PinPromptUiState
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.result.SpendResult

class PINPromptViewModel(
    private val loggedInAccountUseCase: LoggedInAccountUseCase,
    private val authenticationManager: AuthenticationManager,
    private val onLoginSuccess: () -> Unit
): ViewModel() {

    private val enteredPIN = MutableStateFlow("")

    private val _errorChannel = Channel<SessionError?>()
    val errorChannel = _errorChannel.receiveAsFlow()

    private val _uiState = MutableStateFlow(PinPromptUiState("loading"))
    val uiState = combine(
        _uiState,
        authenticationManager.isLockedOut
    ){ui, state ->

        ui.copy(
            isLockedOut = state
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    init {
        loggedInAccountUseCase.y.onEach {res ->
            _uiState.update {
                it.copy(
                    userName = res?.userName ?: "failed y"
                )
            }
        }.launchIn(viewModelScope)
       /* loggedInAccountUseCase.loggedInAccount.onEach {res ->
            _uiState.update {
                it.copy(
                    userName = res.toString()
                )
            }
        }.launchIn(viewModelScope)
*/

        enteredPIN.onEach{pin ->
            if (pin.length == LoginValidator.NUMBER_PIN_LENGTH){
               authenticationManager.login(pin).also {
                   when(it){
                       is SpendResult.Failure -> {
                           _errorChannel.send(it.error)
                           enteredPIN.update { "" }

                           delay(2000)
                           _errorChannel.send(null)
                       }
                       is SpendResult.Success -> {
                           onLoginSuccess()
                       }
                   }
               }
            }
        }.launchIn(viewModelScope)
    }
    fun handleAction(action: PINKeyboardAction) {
        viewModelScope.launch {
            _errorChannel.send(null)
        }

        when(action){
            is PINKeyboardAction.OnNumberClick -> {
                enteredPIN.update {
                    it + action.number
                }
            }
            PINKeyboardAction.OnUndoClick -> {
                enteredPIN.update {
                    it.dropLast(1)
                }
            }
        }
    }

    fun logout() = viewModelScope.launch {
        authenticationManager.logout()
    }
}