package com.mymu.app.firebase

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mymu.app.utils.AppSpacer
import com.mymu.app.utils.PlatformIcon
import kotlinx.coroutines.launch

@Composable
fun GoogleButtonUI(
    onClick: () -> Unit,
    title: String
) {
    val coroutineScope = rememberCoroutineScope()
    Button(
        onClick = {
            coroutineScope.launch {
                onClick()
            }
        },
        modifier = Modifier.padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        PlatformIcon(modifier = Modifier.size(24.dp))
        AppSpacer(width = 8.dp)
        Text(text = title)
    }
}
//
//
//@Composable
//fun MainActionButton(
//    text: String,
//    onClick: () -> Unit,
//    enabled: Boolean = true,
//    imageVector: ImageVector? = null,
//
//    ) {
//
//    val gradient = Brush.horizontalGradient(
//        colors = listOf(
//            Purple40, PurpleGrey40
//        )
//    )
//
//    Button(
//        onClick = onClick,
//        enabled = enabled,
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color.Transparent,
//            contentColor = if (enabled) colorScheme.onPrimary else colorScheme.onSurfaceVariant,
//            disabledContentColor = colorScheme.onSurfaceVariant
//        ),
//        shape = RoundedCornerShape(24.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp)
//            .shadow(8.dp, shape = RoundedCornerShape(24.dp))
//            .background(gradient)
//    ) {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                imageVector?.let {
//                    Icon(
//                        imageVector = it,
//                        contentDescription = null,
//                        modifier = Modifier.padding(end = 8.dp)
//                    )
//                }
//                Text(
//                    text = text,
//                )
//            }
//        }
//    }
//}
//
//
