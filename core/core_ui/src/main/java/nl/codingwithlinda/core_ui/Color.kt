package nl.codingwithlinda.core_ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

val primary = Color(0xFF5A00C8)
val onPrimary = Color(0xFFFFFFFF)
val primaryContainer = Color(0xFF8138ff)
val primaryFixed = Color(0xFFeaddff)

val inversePrimary = Color(0xFFd2bcff)

val secondary = Color(0xFF5f6200)
val secondaryContainer = Color(0xFFd2e750)
val onSecondaryContainer = Color(0xFF414300)

val tertiaryContainer = Color(0xFFc4e0f9)

val error = Color(0xFFa40019)
val onError = Color(0xFFffffff)

val surface = Color(0xFFfcf9f9)
val surfaceContainerLowest = Color(0xFFffffff)
val surfaceContainerLow = Color(0xFFf6f3f3)
val surfaceContainer = Color(0xFFf0eded)
val surfaceContainerHighest = Color(0xFFe4e2e2)
val onSurface = Color(0xFF1b1b1c)
val onSurfaceVariant = Color(0xFF44474b)
val outline = Color(0xFF75777b)

val inverseSurface = Color(0xFF303031)
val inverseOnSurface = Color(0xFFf3f0f0)

val background = Color(0xFFfef7ff)
val onBackground = Color(0xFF1d1a25)

val onPrimaryFixed = Color(0xFF24005a)
val onPrimaryFixedVariant = Color(0xFF5900c7)
val secondaryFixed = Color(0xFFe5ea58)
val secondaryFixedDim = Color(0xFFc9ce3e)
val success = Color(0xFF29ac08)

val purplish = Color(0xFF50337B)
val bluish = Color(0xFF25005d)
//val dashboardBackground = Brush.radialGradient(
//    colors = listOf(
//        onPrimaryFixedVariant,
//        onPrimaryFixed
//    ),
//    center = androidx.compose.ui.geometry.Offset(50f, 150f),
//    radius = Float.POSITIVE_INFINITY,
//    tileMode = TileMode.Clamp
//)
val dashboardBackground = Brush.radialGradient(
    colors = listOf(
        Color(0xFF5A00C8),
        Color(0xFF24005A),
    ),
    center = Offset(x = 0f, y = 1f),
    radius = 1250f
)
/*
val dashboardBackground = Brush.linearGradient(
    colors = listOf(
        bluish.copy(.45f),
        bluish.copy(.85f),
        bluish
    ),
    start= androidx.compose.ui.geometry.Offset(0f, 0f),
    end = androidx.compose.ui.geometry.Offset(400f,800f),
    tileMode = TileMode.Clamp
)
*/








