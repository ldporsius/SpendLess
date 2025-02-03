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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.authentication.core.presentation.components.CustomTextField
import nl.codingwithlinda.core.navigation.NavigationEvent
import nl.codingwithlinda.core.presentation.components.WalletButton


@Composable
fun LoginScreen(
    onNavigate: (NavigationEvent) -> Unit
) {
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
                    ,
                    text = "",
                    onValueChange = {},
                    placeholder = {
                        Text(text = "Username")
                    }
                )
                CustomTextField(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth()
                    ,
                    text = "",
                    onValueChange = {},
                    placeholder = {
                        Text(text = "PIN")
                    }
                )

                Button(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
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