package com.jmuto.forcus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jmuto.forcus.ui.ForCusApp
import com.jmuto.forcus.ui.theme.ForCusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ForCusTheme {
                ForCusApp()
            }
        }
    }
}