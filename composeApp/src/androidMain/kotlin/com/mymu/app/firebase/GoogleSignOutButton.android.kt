package com.mymu.app.firebase

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
actual fun GoogleSignOutNativeButton(
    onLogout: () -> Unit
) {
    val activity = LocalContext.current as Activity
    val googleSignInClient = GoogleSignIn.getClient(activity, GoogleSignInOptions.DEFAULT_SIGN_IN)

    GoogleButtonUI(
        onClick = {
            googleSignInClient.signOut().addOnCompleteListener {
                if (it.isSuccessful) {
                    onLogout()
                } else {
                    Log.e("TAG", "Google sign out failed")
                }
            }
        },
        title = "Sign out from Google"
    )
}
