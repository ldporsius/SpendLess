package nl.codingwithlinda.authentication.registration.user_name.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.authentication.registration.user_name.presentation.state.RegisterAction
import nl.codingwithlinda.authentication.registration.user_name.presentation.state.RegisterUserViewState
import nl.codingwithlinda.core.R
import nl.codingwithlinda.core.navigation.NavigationEvent
import nl.codingwithlinda.core.presentation.components.WalletButton

@Composable
fun RegisterUserNameScreen(
    uistate: RegisterUserViewState,
    onAction: (RegisterAction) -> Unit,
    onNavigate: (NavigationEvent) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {

        WalletButton(
            modifier = Modifier,
            onClick = { }
        )
        Text(text = "Welcome to SpendLess!\n" +
                "How can we address you?",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)

        )
        Text("Create unique username",
            style = MaterialTheme.typography.bodySmall
        )

        OutlinedTextField(
            value = uistate.userNameInput,
            onValueChange = {
                onAction(RegisterAction.NameInput(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            shape = RoundedCornerShape(16.dp),
            textStyle = LocalTextStyle.current.merge(
                MaterialTheme.typography.displaySmall.copy(
                    textAlign = TextAlign.Center
                )
            ),
            placeholder = {
                Text(text = "username",
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)

                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent

            )

        )

        Button(onClick = {
            onNavigate(NavigationEvent.NavToCreatePin(uistate.userNameInput)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 24.dp),
            shape = RoundedCornerShape(16.dp),
            enabled = uistate.isUserNameValid,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Next",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Icon(Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 1.dp)

            )
        }
    }
}