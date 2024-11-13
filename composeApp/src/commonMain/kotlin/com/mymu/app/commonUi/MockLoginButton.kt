package com.mymu.app.commonUi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.mymu.app.firebase.GoogleButtonUI
import com.mymu.app.firebase.GoogleLoginNativeButton
import com.mymu.app.firebase.models.AuthResponse
import com.mymu.app.firebase.models.GoogleAccount
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun MockLoginButton(
    onResponse: (AuthResponse) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()


    GoogleButtonUI(
        onClick = {
            val response: AuthResponse = AuthResponse.Success(GoogleAccount.mock())

            coroutineScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) { onResponse(response) }
            }
        },
        title = "Login "
    )


}