package com.mymu.app.utils

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mymu.app.getPlatform
import mymuapp.composeapp.generated.resources.Res
import mymuapp.composeapp.generated.resources.ic_apple
import mymuapp.composeapp.generated.resources.ic_google
import org.jetbrains.compose.resources.painterResource


@Composable
fun PlatformIcon(modifier: Modifier = Modifier) {
    val iconRes = if (getPlatform().isApple) {
        Res.drawable.ic_apple
    } else {
        Res.drawable.ic_google
    }

    Icon(
        painter = painterResource(iconRes),
        contentDescription = null,
        modifier = modifier,
        tint = Color.Unspecified
    )
}