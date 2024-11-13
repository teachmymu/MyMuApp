package com.mymu.app.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.TopAppBar
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import com.mymu.app.OnMoveToScreenFunction
import com.mymu.app.Screen
import com.mymu.app.ScreenData
import com.mymu.app.firebase.GoogleSignOutButton
import com.mymu.app.firebase.models.SocialAccount
import com.mymu.app.screens.loading.LoadingScreen



data class TabItem(val title: String, val icon: ImageVector)


@Composable
fun MainScreen(account: SocialAccount, onMoveToScreenFunction: OnMoveToScreenFunction) {
    val tabs = listOf(
        TabItem("Tab 1", Icons.Default.Home),
        TabItem("Tab 2", Icons.Default.Favorite),
        TabItem("Tab 3", Icons.Default.Person)
    )
    val selectedTabIndex = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(account, scope)
        }
    ) {
        Column {
            // Top Bar
            TopAppBar(
                title = { Text("App Title") },
                navigationIcon = {
                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )

            // Tab Navigation
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                tabs.forEachIndexed { index, tabItem ->
                    Button(onClick = { selectedTabIndex.value = index }) {
                        Text(tabItem.title)
                    }
                }
            }

            // Content
            Box(modifier = Modifier.fillMaxSize()) {
                when (selectedTabIndex.value) {
                    0 -> Tab1Content(account , onMoveToScreenFunction)
                    1 -> Tab2Content()
                    2 -> Tab3Content()
                }
            }
        }
    }
}

@Composable
fun Tab1Content(account: SocialAccount, onNavigateToSettings: OnMoveToScreenFunction) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome, ${account.profile.name}!", modifier = Modifier.padding(16.dp).clickable {
            onNavigateToSettings(ScreenData(Screen.Settings, account))

        })

        GoogleSignOutButton {
            onNavigateToSettings(ScreenData(Screen.Login, null))

        }
    }
}

@Composable
fun Tab2Content() {
    LoadingScreen()
}

@Composable
fun Tab3Content() {
    LoadingScreen()
}

@Composable
fun DrawerContent(
    account: SocialAccount,
    scope: CoroutineScope
) {
    // Content of the drawer
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Hello, ${account.profile.name}!")
        Button(onClick = {
            scope.launch { /* Close drawer logic */ }
        }) {
            Text("Close Drawer")
        }
    }
}