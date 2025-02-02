package nl.codingwithlinda.core_ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = nl.codingwithlinda.core_ui.primary,
    secondary = nl.codingwithlinda.core_ui.secondary,
    tertiary = nl.codingwithlinda.core_ui.tertiaryContainer,
    background = nl.codingwithlinda.core_ui.background,
    surface = nl.codingwithlinda.core_ui.surface,
    onPrimary = nl.codingwithlinda.core_ui.onPrimary,
    onSecondary = nl.codingwithlinda.core_ui.onSecondaryContainer,
    onTertiary = nl.codingwithlinda.core_ui.onSurface,
    onBackground = nl.codingwithlinda.core_ui.onBackground,
    onSurface = nl.codingwithlinda.core_ui.onSurface,
    primaryContainer = nl.codingwithlinda.core_ui.primaryContainer,

    surfaceContainer = nl.codingwithlinda.core_ui.surfaceContainer,
    surfaceContainerLowest = nl.codingwithlinda.core_ui.surfaceContainerLowest,
    surfaceContainerLow = nl.codingwithlinda.core_ui.surfaceContainerLow,
    surfaceContainerHighest = nl.codingwithlinda.core_ui.surfaceContainerHighest,
    onSurfaceVariant = nl.codingwithlinda.core_ui.onSurfaceVariant,
    inverseSurface = nl.codingwithlinda.core_ui.inverseSurface,
    inverseOnSurface = nl.codingwithlinda.core_ui.inverseOnSurface,

    inversePrimary = nl.codingwithlinda.core_ui.inversePrimary,

    outline = nl.codingwithlinda.core_ui.outline,

    error = nl.codingwithlinda.core_ui.error,
    onError = nl.codingwithlinda.core_ui.onError,

    secondaryContainer = nl.codingwithlinda.core_ui.secondaryContainer,


)

@Composable
fun SpendLessTheme(

    content: @Composable () -> Unit
) {
    val colorScheme =
        LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = nl.codingwithlinda.core_ui.AppTypography,
        content = content
    )
}