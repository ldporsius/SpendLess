package nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.authentication.core.domain.constants.PIN_LENGTH

data class PINUiState(
    val pinLength: Int = PIN_LENGTH,
    val numberInput: List<Int> = emptyList(),
){
    fun pinInputColor(index: Int): Color{
        if (index in numberInput.indices){
            return Color.Black

        }
        return Color.LightGray
    }

    @Composable
    fun PINDots(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(
                12.dp,
                alignment = androidx.compose.ui.Alignment.CenterHorizontally
            )

        ) {
            for (i in 0 until pinLength) {
                Spacer(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            color = pinInputColor(i),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}
