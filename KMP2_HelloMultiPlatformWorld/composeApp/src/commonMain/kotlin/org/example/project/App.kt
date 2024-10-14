package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmp2_hellomultiplatformworld.composeapp.generated.resources.Res
import kmp2_hellomultiplatformworld.composeapp.generated.resources.compose_multiplatform
import moe.tlaster.precompose.PreComposeApp

@Composable
@Preview
fun App() {
    PreComposeApp {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Hi PreCompose!")
            }
            AnimatedVisibility(showContent) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text("Welcome back")
                    Text("To the bench bitches")
                }
            }
        }
    }
}