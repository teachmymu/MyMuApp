package com.mymu.app



sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Main : Screen("main")
    data object Details : Screen("details")
    data object Settings : Screen("settings")
    data object Search : Screen("search")
}

data class ScreenData<T>(val screenName: Screen, val data: T? = null)


typealias OnMoveToScreenFunction = (ScreenData<*>?) -> Unit
