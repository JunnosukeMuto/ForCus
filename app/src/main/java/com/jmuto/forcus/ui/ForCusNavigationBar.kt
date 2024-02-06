package com.jmuto.forcus.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun ForCusNavigationBar(currentDestination: NavDestination?, navigateSingleTopTo: (String) -> Unit) {
    NavigationBar {
        Destination.entries.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                onClick = { navigateSingleTopTo(destination.route) },
                icon = { Icon(imageVector = destination.icon, contentDescription = null) },
                label = { Text(stringResource(id = destination.labelId)) },
            )
        }
    }
}