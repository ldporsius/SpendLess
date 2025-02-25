package nl.codingwithlinda.core_ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = primary,
    secondary = secondary,
    tertiary = tertiaryContainer,
    background = background,
    surface = surface,
    onPrimary = onPrimary,
    onSecondary = onSecondaryContainer,
    onTertiary = onSurface,
    onBackground = onBackground,
    onSurface = onSurface,
    primaryContainer = primaryContainer,

    surfaceContainer = surfaceContainer,
    surfaceContainerLowest = surfaceContainerLowest,
    surfaceContainerLow = surfaceContainerLow,
    surfaceContainerHighest = surfaceContainerHighest,
    onSurfaceVariant = onSurfaceVariant,
    inverseSurface = inverseSurface,
    inverseOnSurface = inverseOnSurface,

    inversePrimary = inversePrimary,

    outline = outline,

    error = error,
    onError = onError,

    secondaryContainer = secondaryContainer,
)
@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)


@OptIn(ExperimentalMaterial3Api::class)
fun segmentedButtonColors() = SegmentedButtonColors(
    activeContainerColor = surfaceContainerLowest,
    activeContentColor = onSurface,
    activeBorderColor = Color.Transparent,
    inactiveContainerColor = Color.Transparent,
    inactiveContentColor = onPrimaryFixed,
    inactiveBorderColor = Color.Transparent,
    disabledActiveContainerColor = surfaceContainer,
    disabledActiveContentColor = onSurface,
    disabledActiveBorderColor = Color.Transparent,
    disabledInactiveContainerColor = surfaceContainer,
    disabledInactiveContentColor = onPrimaryFixed,
    disabledInactiveBorderColor = Color.Transparent,
)

@OptIn(ExperimentalMaterial3Api::class)
val LocalSegmentedButtonColorProvider = compositionLocalOf {
   segmentedButtonColors()
}

val LocalShapeProvider = compositionLocalOf {
    Shapes()
}
@Composable
fun transparentTextFieldColors() = OutlinedTextFieldDefaults.colors(
    unfocusedContainerColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
)

@Composable
fun SpendLessTheme(

    content: @Composable () -> Unit
) {
    val colorScheme =
        LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}