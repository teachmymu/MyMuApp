package com.mymu.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mymu.app.firebase.GoogleLoginButton
import com.mymu.app.firebase.GoogleSignOutButton
import com.mymu.app.firebase.models.AuthResponse
import mymuapp.composeapp.generated.resources.Res
import mymuapp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var userName by remember { mutableStateOf("") }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (userName.isEmpty()) {
                GoogleLoginButton(
                    onResponse = {
                        println("Response: $it")
                        (it as? AuthResponse.Success)?.account?.profile?.let { profile ->
                            userName = profile.name
                        }
                    }

                )
            } else {
                GoogleSignOutButton(
                    onResponse = {
                        if (it is AuthResponse.OnLogout) {
                            userName = ""
                        }

                    }
                )
            }

            AnimatedVisibility(userName.isNotEmpty()) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Hello, $userName!")
                }
            }
        }
    }
}