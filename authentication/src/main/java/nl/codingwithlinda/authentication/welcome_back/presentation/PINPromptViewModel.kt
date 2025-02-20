package nl.codingwithlinda.authentication.welcome_back.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class PINPromptViewModel(
    private val sessionManager: SessionManager
): ViewModel() {


    fun handleAction(action: PINKeyboardAction) {
        when(action){
            is PINKeyboardAction.OnNumberClick -> {

            }
            PINKeyboardAction.OnUndoClick -> {
            }
        }
    }

    fun logout() = viewModelScope.launch {
        sessionManager.endSession()
    }
}