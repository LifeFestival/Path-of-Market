package com.example.pathofmarket.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object PathOfMarketColors {

    // Backgrounds
    val Background = Color(0xFF0D0D0D)        // near black
    val Surface = Color(0xFF1A1410)           // dark brown-black
    val SurfaceVariant = Color(0xFF2A2018)    // slightly lighter brown

    // Primary
    val Primary = Color(0xFFD4A843)           // antique gold
    val PrimaryVariant = Color(0xFF8B6914)    // dark gold
    val OnPrimary = Color(0xFF1A1410)         // dark text on gold

    // Secondary
    val Secondary = Color(0xFF8B1A1A)         // dark red
    val SecondaryVariant = Color(0xFFB22222)  // firebrick red
    val OnSecondary = Color(0xFFFFFFFF)       // white text on red

    // Text
    val OnBackground = Color(0xFFE8D5B0)      // parchment white
    val OnSurface = Color(0xFFC9B99A)         // muted parchment
    val OnSurfaceVariant = Color(0xFF8A7560)  // dimmed text

    // Utility
    val Error = Color(0xFFCF4444)             // muted red for errors
}

// ui/theme/Type.kt
val PathOfMarketTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = PathOfMarketColors.Primary
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = PathOfMarketColors.Primary
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = PathOfMarketColors.OnBackground
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = PathOfMarketColors.OnSurface
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = PathOfMarketColors.OnSurfaceVariant
    )
)

// ui/theme/Theme.kt
private val DarkColorScheme = darkColorScheme(
    primary = PathOfMarketColors.Primary,
    onPrimary = PathOfMarketColors.OnPrimary,
    primaryContainer = PathOfMarketColors.PrimaryVariant,

    secondary = PathOfMarketColors.Secondary,
    onSecondary = PathOfMarketColors.OnSecondary,
    secondaryContainer = PathOfMarketColors.SecondaryVariant,

    background = PathOfMarketColors.Background,
    onBackground = PathOfMarketColors.OnBackground,

    surface = PathOfMarketColors.Surface,
    onSurface = PathOfMarketColors.OnSurface,
    surfaceVariant = PathOfMarketColors.SurfaceVariant,
    onSurfaceVariant = PathOfMarketColors.OnSurfaceVariant,

    error = PathOfMarketColors.Error
)

@Composable
fun PathOfMarketTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = PathOfMarketTypography,
        content = content
    )
}