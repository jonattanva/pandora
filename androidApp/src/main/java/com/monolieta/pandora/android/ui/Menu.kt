package com.monolieta.pandora.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Item(title: String, onClick: () -> Unit) {
    val gradient = Modifier
        .heightIn(64.dp)
        .clickable(onClick = onClick)
        .background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colors.primary,
                    MaterialTheme.colors.primaryVariant
                )
            )
        )
        .padding(16.dp)

    Box(modifier = gradient) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
@Preview(name = "Menu item", showBackground = true)
private fun DefaultPreview() {
    Item("My library") {

    }
}