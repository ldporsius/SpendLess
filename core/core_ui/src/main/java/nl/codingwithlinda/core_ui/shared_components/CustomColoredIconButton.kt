package nl.codingwithlinda.core_ui.shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomColoredIconButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    icon: @Composable () -> Unit
) {

    Box(modifier = modifier
        .background(
            color = backgroundColor, shape = MaterialTheme.shapes.small
        )
        .padding(12.dp)

    ) {
        icon()
    }
}

