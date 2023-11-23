package hr.kristiankliskovic.devcontrol.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = lightColors(
    primary = Color(0xFF546E7A), // Steel Blue
    primaryVariant = Color(0xFF37474F), // Darker Steel Blue
    secondary = Color(0xFF8BC34A), // Lime Green

    background = Color(0xFFF0F4C3), // Greenish Beige
    surface = Color(0xFFD1D9CE), // Light Grayish
    onPrimary = Color(0xFF212121), // Dark Gray
    onSecondary = Color(0xFFFFFFFF), // White
    onBackground = Color(0xFF212121), // Dark Gray
    onSurface = Color(0xFF212121), // Dark Gray

    // Additional colors for various UI elements
//    accent = Color(0xFFFF4081), // Pink accent for highlighting
    error = Color(0xFFE53935), // Red for error messages
//    success = Color(0xFF4CAF50) // Green for success messages
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF546E7A), // Steel Blue
    primaryVariant = Color(0xFF37474F), // Darker Steel Blue
    secondary = Color(0xFF8BC34A), // Lime Green

    background = Color(0xFFF0F4C3), // Greenish Beige
    surface = Color(0xFFD1D9CE), // Light Grayish
    onPrimary = Color(0xFF212121), // Dark Gray
    onSecondary = Color(0xFFFFFFFF), // White
    onBackground = Color(0xFF212121), // Dark Gray
    onSurface = Color(0xFF212121), // Dark Gray

    // Additional colors for various UI elements
//    accent = Color(0xFFFF4081), // Pink accent for highlighting
    error = Color(0xFFE53935), // Red for error messages
//    success = Color(0xFF4CAF50) // Green for success messages
)


@Composable
fun DevControlTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}