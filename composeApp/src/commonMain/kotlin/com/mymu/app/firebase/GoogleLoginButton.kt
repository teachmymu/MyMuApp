package com.mymu.app.firebase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.mymu.app.firebase.models.AuthResponse
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
internal expect fun GoogleLoginNativeButton(
    onResponse: (AuthResponse) -> Unit,
)

@Composable
fun GoogleLoginButton(
    onResponse: (AuthResponse) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    GoogleLoginNativeButton(
        onResponse = { response ->
            coroutineScope.launch(Dispatchers.IO) {
                response.doOnSuccess { account ->
                    Firebase.auth.signInWithCredential(
                        GoogleAuthProvider.credential(account.idToken, null)
                    )
                }

                withContext(Dispatchers.Main) { onResponse(response) }
            }
        }
    )
}

