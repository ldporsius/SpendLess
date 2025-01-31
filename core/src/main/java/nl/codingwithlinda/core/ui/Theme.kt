package nl.codingwithlinda.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

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

)

@Composable
fun SpendLessTheme(

    content: @Composable () -> Unit
) {
    val colorScheme =
        LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}