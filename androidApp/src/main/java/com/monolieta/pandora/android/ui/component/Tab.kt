package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Tab(
    values: List<String>,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        values.forEach { value ->
            Surface(
                modifier = Modifier.padding(0.dp, 0.dp, 4.dp, 0.dp),
                elevation = 4.dp,
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colors.surface
            ) {
                Text(
                    text = value,
                    color = color,
                    modifier = Modifier.padding(8.dp, 4.dp),
                    style = style
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Tab(
        values = listOf("PlayStation 4", "Xbox One")
    )
}