package nl.codingwithlinda.authentication.pin_prompt.presentation.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.authentication.pin_prompt.presentation.PINPromptScreen
import nl.codingwithlinda.authentication.pin_prompt.presentation.state.PinPromptUiState
import nl.codingwithlinda.core_ui.SpendLessTheme

@Preview
@Composable
private fun WelcomeBackScreenPreview() {

    SpendLessTheme {
        PINPromptScreen(
            onPINKeyboardAction = {},
            onLogout = {},
            uiState = PinPromptUiState("preview user name"),
            error = null,
        )

    }
}