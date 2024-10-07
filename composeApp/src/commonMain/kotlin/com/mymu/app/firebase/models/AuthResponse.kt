package com.mymu.app.firebase.models

sealed interface AuthResponse {
    data class Success(val account: GoogleAccount) : AuthResponse
    data class Error(val message: String) : AuthResponse
    data object OnLogout : AuthResponse
    data object Cancelled : AuthResponse

    suspend fun doOnSuccess(block: suspend (GoogleAccount) -> Unit) {
        when (this) {
            is Success -> block(account)
            else -> Unit
        }
    }

    suspend fun doOnError(block: suspend (String) -> Unit) {
        when (this) {
            is Error -> block(message)
            else -> Unit
        }
    }
}
