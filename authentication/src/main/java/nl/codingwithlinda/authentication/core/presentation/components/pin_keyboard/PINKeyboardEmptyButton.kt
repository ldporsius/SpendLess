package nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.ui.primaryFixed

@Composable
fun PINKeyboardEmptyButton(

) {

    Box(modifier = Modifier
        .size(108.dp)
        .background(
            color = Color.Transparent,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
        )
        ,
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Spacer(modifier = Modifier)
    }
}