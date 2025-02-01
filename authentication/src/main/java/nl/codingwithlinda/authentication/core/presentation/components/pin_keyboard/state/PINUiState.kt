package nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state

import androidx.compose.ui.graphics.Color

data class PINUiState(
    val pinLength: Int = 5,
    val numberInput: List<Int> = emptyList(),
){
    fun pinInputColor(index: Int): Color{
        if (index in numberInput.indices){
            return Color.Black

        }
        return Color.LightGray
    }
}
