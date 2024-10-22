package org.example.project.data

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

object SessionCache {
    var configDevice: CrossConfigDevice? = null

    @Composable
    fun isDarkMode(): Boolean {
        return configDevice?.isDarkModeEnable() ?: isSystemInDarkTheme()
    }
}