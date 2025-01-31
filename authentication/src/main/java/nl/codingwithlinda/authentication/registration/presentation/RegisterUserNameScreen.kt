package nl.codingwithlinda.authentication.registration.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.R
import nl.codingwithlinda.core.presentation.components.WalletButton

@Composable
fun RegisterUserNameScreen(

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
            modifier = Modifier
                .size(64.dp),
            icon = R.drawable.account_balance_wallet,
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
    }
}