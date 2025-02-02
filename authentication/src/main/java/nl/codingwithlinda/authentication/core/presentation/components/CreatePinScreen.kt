package nl.codingwithlinda.authentication.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.PINKeyboard
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINUiState
import nl.codingwithlinda.core.presentation.components.WalletButton

@Composable
fun CreatePinScreen(
    pinUiState: PINUiState,
    header: @Composable () -> Unit,
    errorBanner: @Composable (modifier: Modifier) -> Unit,
    onAction: (PINKeyboardAction) -> Unit,
    onNavigate: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        ,
        contentAlignment = Alignment.TopCenter
    ) {
        IconButton(
            onClick = onNavigate,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {


            WalletButton(
                modifier = Modifier.padding(top = 16.dp),
            ) { }

            header()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    12.dp,
                    alignment = androidx.compose.ui.Alignment.CenterHorizontally
                )

            ) {
                for (i in 0 until pinUiState.pinLength) {
                    Spacer(
                        modifier = Modifier
                            .size(16.dp)
                            .background(
                                color = pinUiState.pinInputColor(i),
                                shape = CircleShape
                            )
                    )
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .weight(1f),
                contentAlignment = androidx.compose.ui.Alignment.Center) {
                PINKeyboard(
                    modifier = Modifier,
                    onPINKeyboardAction = {
                        onAction(it)
                    }

                )
            }
        }

        errorBanner(
           Modifier.
               fillMaxWidth()
               .align(androidx.compose.ui.Alignment.BottomCenter)
        )
    }

}