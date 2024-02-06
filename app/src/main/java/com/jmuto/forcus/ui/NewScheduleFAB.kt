package com.jmuto.forcus.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.jmuto.forcus.R

@Composable
fun NewScheduleFAB(currentDestination: NavDestination?, onClick: () -> Unit) {
    AnimatedVisibility(
        visible = currentDestination?.hierarchy?.any { it.route == Destination.SETTINGS.route } == false,
        enter = slideInHorizontally{ fullWidth -> fullWidth / 3 } + fadeIn(),
        exit = slideOutHorizontally{ fullWidth -> fullWidth / 3 } + fadeOut()
    ) {
        ExtendedFloatingActionButton(
            onClick = onClick,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Outlined.Create, null)
                Text(text = stringResource(id = R.string.new_schedule))
            }
        }
    }
}