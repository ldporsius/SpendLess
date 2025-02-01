package nl.codingwithlinda.authentication.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.authentication.onboarding.state.OnboardingUiState

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    uiState: OnboardingUiState,
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
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black
            )
        }

        Column {
            Text(text = "Set SpendLess",
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = "to your preferences",
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = "You can change it at any time in Settings",
                style = MaterialTheme.typography.bodySmall
            )
            ElevatedCard {
                Text(text = uiState.exampleFormattedText)
            }
        }
    }

}