package com.mymu.app.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.mymu.app.OnMoveToScreenFunction
import com.mymu.app.Screen
import com.mymu.app.ScreenData
import com.mymu.app.commonUi.MockLoginButton
import com.mymu.app.firebase.GoogleLoginButton
import com.mymu.app.firebase.models.AuthResponse
import mymuapp.composeapp.generated.resources.Res
import mymuapp.composeapp.generated.resources.login_background
import org.jetbrains.compose.resources.painterResource


    @Composable
    fun LoginScreen(onMoveToScreenFunction: OnMoveToScreenFunction) {
        Box {
            Image(
                painter = painterResource(Res.drawable.login_background),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent,),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    GoogleLoginButton(
                        onResponse = {
                            println("Response: $it")
                            (it as? AuthResponse.Success)?.account?.let { account ->
                                onMoveToScreenFunction(ScreenData(Screen.Main, account))
                            }
                        }

                    )

                MockLoginButton(
                    onResponse = {
                        println("Response: $it")
                        (it as? AuthResponse.Success)?.account?.let { account ->
                            onMoveToScreenFunction(ScreenData(Screen.Main, account))
                        }
                    }
                )

            }
        }
    }
