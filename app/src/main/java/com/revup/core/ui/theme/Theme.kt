package com.revup.core.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = RepairLinkColors.OrangePrimary,
    onPrimary = RepairLinkColors.DarkBlueIcon,
    secondary = RepairLinkColors.ConnectionBlue,
    onSecondary = RepairLinkColors.White,
    background = RepairLinkColors.PetrolBlue,
    onBackground = RepairLinkColors.White,
    surface = Color(0xFF1A4A5E),
    onSurface = RepairLinkColors.White,
    error = Color(0xFFCF6679),
    onError = RepairLinkColors.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF173F4F),
    onPrimary = RepairLinkColors.White,
    secondary = RepairLinkColors.Secondary,
    onSecondary = RepairLinkColors.White,
    background = RepairLinkColors.Bg,
    onBackground = RepairLinkColors.Ink,
    surface = RepairLinkColors.White,
    onSurface = RepairLinkColors.Ink,
    surfaceContainerHighest = RepairLinkColors.White,
    error = Color(0xFFB00020),
    onError = RepairLinkColors.White,
)

@Composable
fun RepairLinkAndroidTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = RepairLinkTypography,
        content = content
    )
}