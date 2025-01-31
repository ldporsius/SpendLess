package nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard.state

sealed interface PINKeyboardAction {
    data class OnNumberClick(val number: Int): PINKeyboardAction
    data object OnUndoClick: PINKeyboardAction

}