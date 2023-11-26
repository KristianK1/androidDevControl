package hr.kristiankliskovic.devcontrol.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF0C5CF7), // Steel Blue
    secondary = Color(0xFFBB96C2), // Lime Green

    background = Color(0xFF000000), // Greenish Beige
    surface = Color(0xFF000000), // Light Grayish
    onPrimary = Color(0xFF212121), // Dark Gray
    onSecondary = Color(0xFFFFFFFF), // White
    onBackground = Color(0xFF212121), // Dark Gray
    onSurface = Color(0xFF212121), // Dark Gray
    inverseSurface = Color(0xFFB8B8B8),
    error = Color(0xFFE53935), // Red for error messages
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF03028E), // Steel Blue
    secondary = Color(0xFF8BC34A), // Lime Green

    background = Color(0xFFF0F4C3), // Greenish Beige
    surface = Color(0xFFD1D9CE), // Light Grayish
    onPrimary = Color(0xFF212121), // Dark Gray
    onSecondary = Color(0xFFFFFFFF), // White
    onBackground = Color(0xFF212121), // Dark Gray
    onSurface = Color(0xFF212121), // Dark Gray
    inverseSurface = Color(0xFF0E0E0E),
    error = Color(0xFFE53935), // Red for error messages
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
