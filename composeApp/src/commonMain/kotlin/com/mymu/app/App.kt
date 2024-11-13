package com.mymu.app

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mymu.app.firebase.models.GoogleAccount
import com.mymu.app.firebase.models.Profile
import com.mymu.app.firebase.models.SocialAccount
import com.mymu.app.screens.login.LoginScreen
import com.mymu.app.screens.main.MainScreen
import com.mymu.app.ui.theme.AppTheme
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    AppTheme {
        NavigationComponent(navController = navController)
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {

    var appScreenData :ScreenData<Any?> = ScreenData(Screen.Main, null)


    var userAccount: SocialAccount? = null

    val firebaseUser =  Firebase.auth.currentUser
    if(firebaseUser != null) {

        val profile :Profile = Profile(
            name = firebaseUser.displayName ?: "",
            familyName = "",
            givenName = "",
            email = firebaseUser.email ?: "",
            picture = firebaseUser.photoURL.toString()
        )
        userAccount = GoogleAccount(firebaseUser.displayName ?: "", "", profile)
    }

    val startDestination = if(userAccount == null) {
        Screen.Login.route
    } else {
        Screen.Main.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            LoginScreen { screenData ->
                if(screenData?.screenName is Screen.Main) {
                    userAccount = screenData.data as SocialAccount
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                        appScreenData = ScreenData(Screen.Main, userAccount)

                    }
                }
            }
        }
        composable(Screen.Main.route) {
            userAccount?.let { user ->
                MainScreen(user,
                    onMoveToScreenFunction = { onMoveToScreenFunction ->
                        appScreenData = ScreenData(Screen.Settings, onMoveToScreenFunction?.data)
                        when (onMoveToScreenFunction?.screenName) {
                            is Screen.Settings -> {
                                navController.navigate(Screen.Settings.route)
                            }
                            is Screen.Search -> {
                                navController.navigate(Screen.Search.route)
                            }
                            is Screen.Login -> {
                                // Logout by the user
                                navController.navigate(Screen.Login.route)
                            }

                            else -> {
                                // Do nothing
                            }
                        }


                    }

                )
            }
        }
        composable(Screen.Settings.route) {
            val name = if(appScreenData.data is SocialAccount) {
                (appScreenData.data as? SocialAccount)?.profile?.name ?: ""
            } else {
                ""
            }
            SettingsScreen(name)
        }
        composable(Screen.Search.route) {
            SearchScreen()
        }
    }
}

@Composable
fun SettingsScreen( name: String) {
    Text(text = "Settings Screen --> $name ")
}

@Composable
fun SearchScreen() {
    Text(text = "Search Screen")
}