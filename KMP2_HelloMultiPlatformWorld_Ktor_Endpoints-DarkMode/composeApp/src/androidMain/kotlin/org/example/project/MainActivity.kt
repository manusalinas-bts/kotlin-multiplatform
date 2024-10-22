package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetColorStatusBar()
            App()
        }
    }
}

@Composable
private fun SetColorStatusBar() {
    val systemUIController = rememberSystemUiController()
    val isDarkMode = isSystemInDarkTheme()

    DisposableEffect(systemUIController, isDarkMode) {
        systemUIController.setStatusBarColor(
            color = if(isDarkMode) Color(0XFF1E1C1C) else Color.White,
            darkIcons = !isDarkMode
        )
        onDispose {  }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}