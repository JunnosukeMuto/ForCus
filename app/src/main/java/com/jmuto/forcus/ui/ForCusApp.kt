package com.jmuto.forcus.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jmuto.forcus.ui.screens.HomeScreen
import com.jmuto.forcus.ui.screens.ScheduleScreen
import com.jmuto.forcus.ui.screens.SettingsScreen

@Composable
fun ForCusApp() {
    // ナビゲーション関係
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    // UI関係
    var isBottomSheetShown by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ForCusTopAppBar()
        },
        bottomBar = {
            ForCusNavigationBar(
                currentDestination = currentDestination,
                navigateSingleTopTo = {route -> navController.navigateSingleTopTo(route)}
            )
        },
        floatingActionButton = {
            NewScheduleFAB(
                currentDestination = currentDestination,
                onClick = { isBottomSheetShown = true}
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destination.HOME.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Destination.HOME.route) {
                HomeScreen()
            }
            composable(route = Destination.SCHEDULE.route) {
                ScheduleScreen()
            }
            composable(route = Destination.SETTINGS.route) {
                SettingsScreen()
            }
        }

        ScheduleBottomSheet(
            onDismissRequest = { isBottomSheetShown = false },
            isShown = isBottomSheetShown
        )
    }
}

// navigate()の代わりにこれを使うといいらしい
fun NavController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }