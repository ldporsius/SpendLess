package nl.codingwithlinda.core_ui.shared_components

import androidx.compose.foundation.ScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.verticalScrollBar(
    scrollState: ScrollState,
    viewPortHeight: Float,
    width: Dp = 2.dp,
    color: Color
) = drawWithContent {
    drawContent()
    if (scrollState.maxValue == 0) {
        return@drawWithContent
    }
    val contentHeight = size.height
    val scrollBarHeight = viewPortHeight * viewPortHeight / contentHeight
    val scrollHeight = viewPortHeight - scrollBarHeight
    val scrollOffset = scrollHeight * scrollState.value / scrollState.maxValue
    drawRect(
        color,
        Offset(size.width - width.toPx(), scrollState.value.toFloat() + scrollOffset),
        Size(width.toPx(), scrollBarHeight)
    )
}