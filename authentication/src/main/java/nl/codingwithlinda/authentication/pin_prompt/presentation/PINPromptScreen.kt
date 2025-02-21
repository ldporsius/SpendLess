package nl.codingwithlinda.authentication.pin_prompt.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.PINKeyboard
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.authentication.core.presentation.error.toUiText
import nl.codingwithlinda.authentication.pin_prompt.presentation.state.PinPromptUiState
import nl.codingwithlinda.core.domain.error.authentication_error.SessionError
import nl.codingwithlinda.core_ui.shared_components.ErrorBanner
import nl.codingwithlinda.core_ui.shared_components.WalletButton

@Composable
fun PINPromptScreen(
    uiState: PinPromptUiState,
    onPINKeyboardAction: (PINKeyboardAction) -> Unit,
    error: SessionError? = null,

    onLogout: () -> Unit
) {

    Scaffold { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding),
            contentAlignment = Alignment.BottomCenter
        ) {


            IconButton(
                onClick = { onLogout() },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Image(
                   painter = painterResource(nl.codingwithlinda.core_ui.R.drawable.logout),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .consumeWindowInsets(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                WalletButton {  }

                uiState.Header()

                PINKeyboard {
                    onPINKeyboardAction(it)
                }
            }

            error?.let {
                ErrorBanner(
                    error = it.toUiText(),
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }

    }
}