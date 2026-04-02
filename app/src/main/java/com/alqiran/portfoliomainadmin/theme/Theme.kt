package com.alqiran.portfoliomainadmin.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF3B82F6),
    onPrimary = Color(0xFF020617),
    background = Color(0xFF020617),
    onBackground = Color(0xFFF8FAFC),
    surface = Color(0xFF0F172A),
    onSurface = Color(0xFFF8FAFC),
    secondary = Color(0xFF0EA5E9),
    onSecondary = Color(0xFFF8FAFC),
    tertiary = Color(0xFF94A3B8),
    onSurfaceVariant = Color(0xFF94A3B8),
    outline = Color(0xFF1E293B),
    error = Color(0xFFEF4444),
    onError = Color(0xFFF8FAFC)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF3B82F6),
    onPrimary = Color(0xFFF8FAFC),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF020617),
    surface = Color(0xFFF1F5F9),
    onSurface = Color(0xFF020617),
    secondary = Color(0xFF0EA5E9),
    onSecondary = Color(0xFF020617),
    outline = Color(0xFFE2E8F0)
)

@Composable
fun PortfolioMainTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}