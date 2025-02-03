package nl.codingwithlinda.authentication.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core_ui.shared_components.CustomTextField
import nl.codingwithlinda.authentication.login.presentation.state.LoginAction
import nl.codingwithlinda.authentication.login.presentation.state.LoginViewState
import nl.codingwithlinda.core.navigation.NavigationEvent
import nl.codingwithlinda.core_ui.shared_components.WalletButton


@Composable
fun LoginScreen(
    uistate: LoginViewState,
    onAction: (LoginAction) -> Unit,
    onNavigate: (NavigationEvent) -> Unit
) {
    val focusRequester = FocusRequester()

    LaunchedEffect(true){
        focusRequester.requestFocus()
    }
    Scaffold {paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 48.dp, horizontal = 16.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WalletButton {  }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Welcome back!",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = "Enter your login details",
                    style = MaterialTheme.typography.bodySmall
                )

                CustomTextField(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    text = uistate.userNameInput,
                    onValueChange = {
                        onAction(LoginAction.UserNameInputAction(it))
                    },
                    placeholder = {
                        Text(text = "Username")
                    },
                    isSingleLine = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                )
                CustomTextField(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth(),
                    text = uistate.pinInput,
                    onValueChange = {
                        onAction(LoginAction.PINInputAction(it))
                    },
                    placeholder = {
                        Text(text = "PIN")
                    },
                    isSingleLine = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                )

                Button(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    enabled = uistate.isLoginValid
                ) {
                    Text(text = "Login")
                }
                TextButton(onClick = { onNavigate(NavigationEvent.NavToRegisterUserName) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    Text(text = "New to SpendLess?")
                }
            }
        }

    }

}