package com.mymu.app.firebase

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.mymu.app.firebase.models.AuthResponse
import com.mymu.app.firebase.models.GoogleAccount
import com.mymu.app.firebase.models.Profile


@Composable
internal actual fun GoogleLoginNativeButton(
    onResponse: (AuthResponse) -> Unit,
) {
    // ./gradlew signingReport to get the SHA-1 key
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("860120495330-5ts7mnu5tn73euucrvfj4jjg7t5auv2h.apps.googleusercontent.com")
        .requestEmail()
        .build()

    val activity = LocalContext.current as Activity
    val googleSignInClient = GoogleSignIn.getClient(activity, gso)


    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val account = task.getResult(ApiException::class.java)

                onResponse(AuthResponse.Success(account.googleAccount))
            } catch (e: ApiException) {
                if (result.resultCode == Activity.RESULT_CANCELED) {
                    AuthResponse.Cancelled
                } else {
                    AuthResponse.Error(e.fullErrorMessage)
                }.also(onResponse)

                Log.w("TAG", "Google sign in failed", e)
            }
        }

    GoogleButtonUI(
        onClick = { launcher.launch(googleSignInClient.signInIntent) }, title = "Login with Google",
    )
}

private val GoogleSignInAccount.googleAccount: GoogleAccount
    get() = GoogleAccount(
        idToken = idToken.orEmpty(),
        accessToken = serverAuthCode.orEmpty(),
        profile = Profile(
            name = displayName.orEmpty(),
            familyName = familyName.orEmpty(),
            givenName = givenName.orEmpty(),
            email = email.orEmpty(),
            picture = photoUrl?.toString().orEmpty()
        ),
    )

private val ApiException.fullErrorMessage: String
    get() {
        return listOfNotNull(
            "code: $statusCode",
            message?.let { "message: $message" },
            "localizedMessage: $localizedMessage",
            "status: $status",
        ).joinToString("\n")
    }
