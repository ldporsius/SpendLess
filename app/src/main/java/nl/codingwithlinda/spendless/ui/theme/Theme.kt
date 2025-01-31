package nl.codingwithlinda.spendless.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import nl.codingwithlinda.core.ui.AppTypography
import nl.codingwithlinda.core.ui.background
import nl.codingwithlinda.core.ui.onBackground
import nl.codingwithlinda.core.ui.onPrimary
import nl.codingwithlinda.core.ui.onSecondaryContainer
import nl.codingwithlinda.core.ui.onSurface
import nl.codingwithlinda.core.ui.primary
import nl.codingwithlinda.core.ui.primaryContainer
import nl.codingwithlinda.core.ui.secondary
import nl.codingwithlinda.core.ui.surface
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
    primaryContainer = primaryContainer

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