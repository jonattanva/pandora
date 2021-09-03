package com.monolieta.pandora.android.ui.component

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monolieta.pandora.android.R

@Composable
fun Information(@DrawableRes cover: Int, title: String, subtitle: String) {
    Information(title, subtitle) {
        Cover(
            painter = painterResource(cover),
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
fun Information(url: String, title: String, subtitle: String) {
    Information(title, subtitle) {
        Cover(
            url,
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
private fun Information(title: String, subtitle: String, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        content()

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitle,
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun Preview() {
    Information(
        cover = R.drawable.psychonauts,
        title = "Psychonauts 2",
        subtitle = "Double Fine Productions",
    )
}