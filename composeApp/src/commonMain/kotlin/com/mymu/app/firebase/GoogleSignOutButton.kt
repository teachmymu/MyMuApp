package com.mymu.app.firebase

import androidx.compose.runtime.Composable
import com.mymu.app.firebase.models.AuthResponse


@Composable
expect fun GoogleSignOutNativeButton(onLogout: () -> Unit)


@Composable
fun GoogleSignOutButton(
    onResponse: (AuthResponse) -> Unit
) {
    GoogleSignOutNativeButton(
        onLogout = {
            onResponse(AuthResponse.OnLogout)
        }
    )
}