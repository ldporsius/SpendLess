package nl.codingwithlinda.authentication.create_pin.presentation.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard.PINKeyboard
import nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard.PINKeyboardButton

@Preview
@Composable
private fun PINButtonPreview() {

    PINKeyboardButton(
        text = {
            Text("1",
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Preview
@Composable
private fun PinKeyboardPreview() {
    PINKeyboard(
        modifier = androidx.compose.ui.Modifier,
        onPINKeyboardAction = {

        }

    )
}