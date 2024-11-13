// New file: AppTheme.kt in ui/theme package
package com.mymu.app.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme

//private val LightColors = lightColors(
//    primary = PrimaryColor,
//    onPrimary = OnPrimaryColor,
//    secondary = SecondaryColor,
//    onSecondary = OnSecondaryColor
//)
//
//private val DarkColors = darkColors(
//    primary = Color(0xFFBB86FC),
//    onPrimary = Color.Black,
//    secondary = SecondaryColor,
//    onSecondary = OnSecondaryColor
//)


private val DarkColorScheme = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,
    onPrimary = Color.White,
)

private val LightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,
    onPrimary = Color.White,

)

private val AppTypography = Typography(
    // Define typography here, such as h1, h2, body1, etc.
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) LightColorScheme else LightColorScheme,
        typography = AppTypography,
        content = content
    )
}
