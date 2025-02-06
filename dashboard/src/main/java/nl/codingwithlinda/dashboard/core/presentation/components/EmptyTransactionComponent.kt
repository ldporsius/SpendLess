package nl.codingwithlinda.dashboard.core.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun EmptyTransactionComponent(modifier: Modifier = Modifier) {
    Text(String(Character.toChars(0x1f4b8)),
        fontSize = 100.sp
    )
}