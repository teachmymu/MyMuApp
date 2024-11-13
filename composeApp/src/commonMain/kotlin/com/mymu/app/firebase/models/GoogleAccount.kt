package com.mymu.app.firebase.models


interface SocialAccount {
    val profile: Profile
}

data class GoogleAccount(
    val idToken: String,
    val accessToken: String,
    override val profile: Profile
) : SocialAccount {
    companion object {
        fun mock(): GoogleAccount {
            return GoogleAccount(
                idToken = "mockIdToken",
                accessToken = "mockAccessToken",
                profile = Profile(
                    name = "mockName",
                    familyName = "mockFamilyName",
                    givenName = "mockGivenName",
                    email = "mockEmail",
                    picture = "https://lh3.googleusercontent.com/a/ACg8ocLteOk37GzZlUdHU67khFypk2OvUMnLjFBUtlDb5KQs9vNa_Rw_JQ=s96-c))"
                )
            )
        }
    }
}

//https://lh3.googleusercontent.com/a/ACg8ocLteOk37GzZlUdHU67khFypk2OvUMnLjFBUtlDb5KQs9vNa_Rw_JQ=s96-c))

data class Profile(
    val name: String,
    val familyName: String,
    val givenName: String,
    val email: String,
    val picture: String?
)




