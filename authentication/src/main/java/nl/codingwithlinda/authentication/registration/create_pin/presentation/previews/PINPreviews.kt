package nl.codingwithlinda.authentication.registration.create_pin.presentation.previews

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.PINKeyboard
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.PINKeyboardButton

@Preview
@Composable
private fun PINButtonPreview() {

    PINKeyboardButton(
        text = {
            Text("1",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Preview
@Composable
private fun PinKeyboardPreview() {
    PINKeyboard(
        modifier = Modifier,
        onPINKeyboardAction = {

        }

    )
}