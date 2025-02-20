package nl.codingwithlinda.authentication.welcome_back.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class PINPromptViewModel(
    private val sessionManager: SessionManager,
): ViewModel() {

    private val enteredPIN = MutableStateFlow("")

    init {
        enteredPIN.onEach{pin ->
            if (pin.length == LoginValidator.NUMBER_PIN_LENGTH){
               sessionManager.login(pin){res ->
                   when(res){
                       is SpendResult.Failure -> TODO()
                       is SpendResult.Success -> TODO()
                   }
               }
            }
        }.launchIn(viewModelScope)
    }
    fun handleAction(action: PINKeyboardAction) {
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
        sessionManager.endSession()
    }
}