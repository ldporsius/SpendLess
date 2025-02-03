package nl.codingwithlinda.core_ui.shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core_ui.util.UiText

@Composable
fun ErrorBanner(
    modifier: Modifier = Modifier,
    error: UiText
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.error)
            .padding(top = 16.dp, bottom = 32.dp)
    ) {

        Text(text = error.asString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onError,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center

        )
    }
}