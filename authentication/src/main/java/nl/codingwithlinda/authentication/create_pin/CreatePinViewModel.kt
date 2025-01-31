package nl.codingwithlinda.authentication.create_pin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard.state.PINUiState

class CreatePinViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(PINUiState())
    val uiState = _uiState.asStateFlow()


    fun handleAction(action: PINKeyboardAction){
        when(action){
            is PINKeyboardAction.OnNumberClick -> {
                _uiState.value = _uiState.value.copy(
                    numberInput = _uiState.value.numberInput + action.number
                )
            }
            PINKeyboardAction.OnUndoClick -> {
                _uiState.value = _uiState.value.copy(
                    numberInput = _uiState.value.numberInput.dropLast(1)
                )
            }
        }
    }
}