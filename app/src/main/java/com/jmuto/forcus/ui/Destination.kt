package com.jmuto.forcus.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.jmuto.forcus.R

enum class Destination(val route: String, val icon: ImageVector, @StringRes val labelId: Int) {
    HOME("home", Icons.Outlined.Home, R.string.home),
    SCHEDULE("schedule", Icons.Outlined.DateRange, R.string.schedule),
    SETTINGS("settings", Icons.Outlined.Settings, R.string.settings),
}