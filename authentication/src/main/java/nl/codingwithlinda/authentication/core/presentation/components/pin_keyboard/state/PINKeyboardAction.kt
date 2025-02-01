package nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state

sealed interface PINKeyboardAction {
    data class OnNumberClick(val number: Int): PINKeyboardAction
    data object OnUndoClick: PINKeyboardAction

}