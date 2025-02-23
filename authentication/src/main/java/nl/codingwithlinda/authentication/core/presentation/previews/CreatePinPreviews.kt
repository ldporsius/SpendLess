package nl.codingwithlinda.authentication.core.presentation.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINUiState
import nl.codingwithlinda.authentication.registration.common.CreatePinScreen
import nl.codingwithlinda.core_ui.util.UiText
import nl.codingwithlinda.core_ui.shared_components.ErrorBanner

@Preview
@Composable
private fun CreatePinScreenPreview() {
    CreatePinScreen(
        pinUiState = PINUiState(),
        header = {},
        errorBanner = {
            ErrorBanner(
                modifier = it.fillMaxWidth(),
                error = UiText.DynamicText("Error")
            )
        },
        onAction = {},
        onNavigate = {}
    )
}