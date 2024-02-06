package com.jmuto.forcus.ui.screens

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.jmuto.forcus.AppBlockService
import com.jmuto.forcus.MainActivity


@Composable
fun HomeScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Button(onClick = {
            val intent = Intent(context, AppBlockService::class.java)
            context.startService(intent)
        }) {
            Text("start service")
        }
        Button(onClick = {
            val intent = Intent(context, AppBlockService::class.java)
            context.stopService(intent)
        }) {
            Text("stop service")
        }
        Button(onClick = {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            context.startActivity(intent)
        }) {
            Text("grand permission")
        }


    }
}