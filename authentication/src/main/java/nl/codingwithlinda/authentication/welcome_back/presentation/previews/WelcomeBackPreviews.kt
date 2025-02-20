package nl.codingwithlinda.authentication.welcome_back.presentation.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.authentication.welcome_back.presentation.PINPromptScreen
import nl.codingwithlinda.core_ui.SpendLessTheme

@Preview
@Composable
private fun WelcomeBackScreenPreview() {

    SpendLessTheme {
        PINPromptScreen(
            onPINKeyboardAction = {},
            userName = "lin",
            onLogout = {},
        )

    }
}