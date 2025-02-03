package nl.codingwithlinda.authentication.login.presentation.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.authentication.login.presentation.LoginScreen
import nl.codingwithlinda.core_ui.SpendLessTheme

@Preview
@Composable
private fun LoginScreenPreview() {

    SpendLessTheme {
        LoginScreen(
            onNavigate = {}
        )
    }
}