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
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINUiState
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.authentication.pin_prompt.presentation.state.PinPromptUiState
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes

class PINPromptViewModel(
    private val loggedInAccountUseCase: LoggedInAccountUseCase,
    private val authenticationManager: AuthenticationManager,
    private val onLoginSuccess: () -> Unit
): ViewModel() {

    private val enteredPIN = MutableStateFlow("")
    private val _pinUiState = MutableStateFlow(PINUiState())
    val pinUiState = combine(
        enteredPIN,
        authenticationManager.isLockedOut,
        _pinUiState) { pin, lockedOut, uiState ->
        if (!lockedOut) {
            uiState.copy(
                numberInput = pin.map { it.digitToInt() }
            )
        }
        else
            uiState
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _pinUiState.value)

    private val _errorChannel = Channel<SessionError?>()
    val errorChannel = _errorChannel.receiveAsFlow()

    private val _uiState = MutableStateFlow(PinPromptUiState(""))
    val uiState = combine(
        _uiState,
        authenticationManager.isLockedOut,
        authenticationManager.remainingLockoutTime
    ){ui, state , remainingTime ->

        ui.copy(
            isLockedOut = state,
            retryTime = remainingTime.milliseconds.toComponents { minutes, seconds, nanoseconds ->
                "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2,'0')}"
            }
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    init {
        loggedInAccountUseCase.loggedInAccount.onEach {res ->
            when(res){
                is SpendResult.Failure -> {
                    _errorChannel.send(res.error)
                }
                is SpendResult.Success -> {
                    _uiState.update {
                        it.copy(
                            userName = res.data?.userName ?: "no account"
                        )
                    }
                }
            }

        }.launchIn(viewModelScope)

        enteredPIN.onEach{pin ->
            if (pin.length == LoginValidator.NUMBER_PIN_LENGTH){
               authenticationManager.login(pin).also {res ->
                   when(res){
                       is SpendResult.Failure -> {
                           if (res.error !is SessionError.SessionLockedError){
                               _errorChannel.send(res.error)
                               delay(2000)
                               _errorChannel.send(null)
                           }
                           enteredPIN.update { "" }

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

        when(action){
            is PINKeyboardAction.OnNumberClick -> {
                enteredPIN.update {
                    (it + action.number).take(LoginValidator.NUMBER_PIN_LENGTH)
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