package com.mymu.app.firebase

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


