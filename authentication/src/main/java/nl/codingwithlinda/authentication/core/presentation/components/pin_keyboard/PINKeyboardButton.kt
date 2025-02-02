package nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core_ui.primaryFixed

@Composable
fun PINKeyboardButton(
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier
        .size(108.dp)
        .background(
            color = nl.codingwithlinda.core_ui.primaryFixed,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
        )
        ,
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        text()
    }
}