package nl.codingwithlinda.authentication.registration.repeat_pin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RepeatPinHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(
            "Repeat Your PIN",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            "Enter your PIN again",
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp)
        )
    }

}