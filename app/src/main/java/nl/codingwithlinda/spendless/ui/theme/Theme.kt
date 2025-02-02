package nl.codingwithlinda.spendless.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import nl.codingwithlinda.core.ui.AppTypography
import nl.codingwithlinda.core.ui.background
import nl.codingwithlinda.core.ui.inverseOnSurface
import nl.codingwithlinda.core.ui.inversePrimary
import nl.codingwithlinda.core.ui.inverseSurface
import nl.codingwithlinda.core.ui.onBackground
import nl.codingwithlinda.core.ui.onPrimary
import nl.codingwithlinda.core.ui.onSecondaryContainer
import nl.codingwithlinda.core.ui.onSurface
import nl.codingwithlinda.core.ui.onSurfaceVariant
import nl.codingwithlinda.core.ui.outline
import nl.codingwithlinda.core.ui.primary
import nl.codingwithlinda.core.ui.primaryContainer
import nl.codingwithlinda.core.ui.secondary
import nl.codingwithlinda.core.ui.secondaryContainer
import nl.codingwithlinda.core.ui.surface
import nl.codingwithlinda.core.ui.surfaceContainer
import nl.codingwithlinda.core.ui.surfaceContainerHighest
import nl.codingwithlinda.core.ui.surfaceContainerLow
import nl.codingwithlinda.core.ui.surfaceContainerLowest
import nl.codingwithlinda.core.ui.tertiaryContainer

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

    error = nl.codingwithlinda.core.ui.error,
    onError = nl.codingwithlinda.core.ui.onError,

    secondaryContainer = secondaryContainer,


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