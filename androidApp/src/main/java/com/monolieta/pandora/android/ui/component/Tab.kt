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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SwipeTextTab(
    values: List<String>,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current
) {
    SwipeTab {
        values.forEach { value ->
            Container {
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

@Composable
fun SwipeImageTab(values: List<String>) {
    SwipeTab {
        values.forEach { value ->
            Container {
                Cover(value)
            }
        }
    }
}

@Composable
fun SwipeDrawableTab(values: List<Int>) {
    SwipeTab {
        values.forEach { value ->
            Container {
                Cover(painterResource(value))
            }
        }
    }
}

@Composable
private fun Container(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.padding(0.dp, 0.dp, 4.dp, 0.dp),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.surface
    ) {
        content()
    }
}

@Composable
private fun SwipeTab(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SwipeTextTab(
        values = listOf("PlayStation 4", "Xbox One")
    )
}