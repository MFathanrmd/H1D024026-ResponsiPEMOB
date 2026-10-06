package com.example.pokemonapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PokemonRed,
    onPrimary = TextPrimary,
    primaryContainer = PokemonRedDark,
    onPrimaryContainer = TextPrimary,
    secondary = PokemonYellow,
    onSecondary = DarkBackground,
    secondaryContainer = PokemonGold,
    onSecondaryContainer = TextPrimary,
    tertiary = PokemonBlueLight,
    onTertiary = TextPrimary,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkCard,
    onSurfaceVariant = TextSecondary,
    error = PokemonRedLight,
    onError = TextPrimary
)

private val LightColorScheme = lightColorScheme(
    primary = PokemonRed,
    onPrimary = TextPrimary,
    primaryContainer = PokemonRedLight,
    onPrimaryContainer = DarkBackground,
    secondary = PokemonYellow,
    onSecondary = DarkBackground,
    background = TextPrimary,
    onBackground = DarkBackground,
    surface = TextPrimary,
    onSurface = DarkBackground,
)

@Composable
fun PokemonAppTheme(
    darkTheme: Boolean = true, // Default dark theme
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DarkBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
