package nl.codingwithlinda.authentication.registration.repeat_pin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINUiState
import nl.codingwithlinda.core.domain.NUMBER_PIN_LENGTH

class RepeatPinViewModel(
    private val userName: String,
    private val pin: String,
    private val navToOnboarding: () -> Unit
): ViewModel() {

    private val _pinEntered = MutableStateFlow<List<Int>>(emptyList())
    private val _uiState = MutableStateFlow(PINUiState())
    val uiState = _uiState.combine(_pinEntered){state, pin ->
        state.copy(
            numberInput = pin
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)


    init {
        _pinEntered
            .debounce(1000)
            .onEach{
                if(it.size == NUMBER_PIN_LENGTH){
                    //navToRepeat(it.joinToString(""))
                }
            }.launchIn(viewModelScope)
    }
    fun handleAction(action: PINKeyboardAction){
        when(action){
            is PINKeyboardAction.OnNumberClick -> {
                _pinEntered.update {
                    it + action.number
                }
            }
            PINKeyboardAction.OnUndoClick -> {
                _pinEntered.update {
                    it.dropLast(1)
                }
            }
        }
    }

    private fun isPinValid(pinEntered: String): Boolean{
        return pinEntered == pin
    }
}