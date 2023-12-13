package hr.kristiankliskovic.devcontrol.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF0C5CF7),
    secondary = Color(0xFFBB96C2),

    background = Color(0xFF000000),
    surface = Color(0xFF000000),
    onPrimary = Color(0xFF212121),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF212121),
    inverseSurface = Color(0xFFB8B8B8),
    error = Color(0xFFE53935),
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF5251F0),
    secondary = Color(0xFF8BC34A),

    background = Color(0xFFF0F0F0),
    surface = Color(0xFFF0F0F0),
    onPrimary = Color(0xFF212121),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF212121),
    inverseSurface = Color(0xFF0E0E0E),
    error = Color(0xFFE53935),
)


@Composable
fun DevControlTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
