package nl.codingwithlinda.authentication.create_pin.presentation.pin_keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.ui.primaryFixed

@Composable
fun PINKeyboardUndoButton(
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier
        .size(108.dp)
        .background(
            color = primaryFixed.copy(.3f),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
        )
        ,
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        text()
    }
}