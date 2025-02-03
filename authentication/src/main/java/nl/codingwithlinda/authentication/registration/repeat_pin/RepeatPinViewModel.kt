package nl.codingwithlinda.authentication.registration.repeat_pin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINUiState
import nl.codingwithlinda.authentication.core.presentation.util.toUiText
import nl.codingwithlinda.core.data.AccountFactory
import nl.codingwithlinda.core.domain.NUMBER_PIN_LENGTH
import nl.codingwithlinda.core.domain.error.authentication.PINDiffersError
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core_ui.util.UiText

class RepeatPinViewModel(
    private val accountFactory: AccountFactory,
    private val userName: String,
    private val pin: String,
    private val navToOnboarding: (Account) -> Unit
): ViewModel() {

    private val _pinEntered = MutableStateFlow<List<Int>>(emptyList())
    private val _uiState = MutableStateFlow(PINUiState())
    val uiState = _uiState.combine(_pinEntered){state, pin ->
        state.copy(
            numberInput = pin
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    private val _errorChannel = Channel<UiText?>()
    val errorChannel = _errorChannel.receiveAsFlow()

    init {
        _pinEntered
            .debounce(1000)
            .onEach{
                if(it.size == NUMBER_PIN_LENGTH){
                   val validRes = isPinValid(it.joinToString(""))
                    if(validRes is SpendResult.Success){
                        val account = accountFactory.create(userName, pin)
                        navToOnboarding(account)
                    }
                    if(validRes is SpendResult.Failure){
                        _errorChannel.send(validRes.error.toUiText())
                        _pinEntered.update { emptyList() }
                        delay(2000L)
                        _errorChannel.send(null)
                    }
                }
            }.launchIn(viewModelScope)
    }
    fun handleAction(action: PINKeyboardAction){
        when(action){
            is PINKeyboardAction.OnNumberClick -> {
                _pinEntered.update {
                    (it + action.number).take(NUMBER_PIN_LENGTH)
                }
            }
            PINKeyboardAction.OnUndoClick -> {
                _pinEntered.update {
                    it.dropLast(1)
                }
            }
        }
    }

    private fun isPinValid(pinEntered: String): SpendResult<Boolean, PINDiffersError>{
        if(pinEntered != pin){
            return SpendResult.Failure(PINDiffersError)
        }
        return SpendResult.Success(true)
    }
}