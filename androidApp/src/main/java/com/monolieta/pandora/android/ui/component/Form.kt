package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monolieta.pandora.android.ui.theme.PandoraTheme

@Composable
fun Form(loading: Boolean, Content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Content()
        }

        if (loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .pointerInput(Unit) { }
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
@Preview(name = "Dark Mode")
fun FormDarkMode() {
    PandoraTheme(darkTheme = true) {
        Form(false) {
            Text(text = "Hello Android")
        }
    }
}

@Composable
@Preview(name = "Light Mode")
fun FormLightMode() {
    PandoraTheme(darkTheme = false) {
        Form(false) {
            Text(text = "Hello Android")
        }
    }
}