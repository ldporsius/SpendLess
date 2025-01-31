package nl.codingwithlinda.authentication.create_pin.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard.PINKeyboard
import nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard.state.PINUiState
import nl.codingwithlinda.core.presentation.components.WalletButton

@Composable
fun CreatePinScreen(
    pinUiState: PINUiState,
    onAction: (PINKeyboardAction) -> Unit,
    onNavigate: () -> Unit
) {
    Box(modifier = Modifier.navigationBarsPadding()) {
        IconButton(
            onClick = onNavigate,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(Icons.Default.ArrowBack,
                contentDescription = null)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {


            WalletButton(
                modifier = Modifier.padding(top = 16.dp),
                icon = nl.codingwithlinda.core.R.drawable.account_balance_wallet
            ) { }
            Text(
                "Create PIN",
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                "Use PIN to login to your account",
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 12.dp)
            )

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
                .padding(top = 16.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center) {
                PINKeyboard(
                    modifier = Modifier,
                    onPINKeyboardAction = {
                        onAction(it)
                    }

                )
            }
        }
    }

}