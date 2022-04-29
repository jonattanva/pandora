package com.monolieta.pandora.android.ui.home

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Cover

@Composable
fun DetailView() {
}

@Composable
private fun Banner(@DrawableRes banner: Int, @DrawableRes cover: Int, title: String, subtitle: String) {
    val color = getVibrantColor(
        bitmap = ImageBitmap.imageResource(banner)
            .asAndroidBitmap()
    )

    Banner(color) {
        Cover(
            painter = painterResource(banner),
            modifier = Modifier.fillMaxWidth()
        )

        Information(
            cover = cover,
            title = title,
            subtitle = subtitle
        )
    }
}

@Composable
private fun Banner(banner: String, cover: String, title: String, subtitle: String) {
    val modifier = Modifier
    Banner(modifier) {
        Cover(
            url = banner,
            modifier = Modifier.fillMaxWidth(),
            onSuccess = { bitmap ->
                val color = getVibrantColor(bitmap)
                modifier.background(
                    Brush.verticalGradient(
                        listOf(color, Color.Black)
                    )
                )
            }
        )

        Information(
            url = cover,
            title = title,
            subtitle = subtitle
        )
    }
}

@Composable
private fun Banner(color: Color, content: @Composable () -> Unit) {
    Banner(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(color, Color.Black)
                )
            )
    ) {
        content()
    }
}

@Composable
private fun Banner(modifier: Modifier, content: @Composable () -> Unit) {
    Column(modifier) {
        content()
    }
}

@Composable
private fun Information(@DrawableRes cover: Int, title: String, subtitle: String) {
    Information(title, subtitle) {
        Cover(
            painter = painterResource(cover),
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
private fun Information(url: String, title: String, subtitle: String) {
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

private fun getVibrantColor(bitmap: Bitmap): Color {
    val palette = Palette.from(bitmap)
        .generate()

    return palette.vibrantSwatch?.rgb?.let {
        Color(it)
    } ?: Color.Transparent
}

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
private fun Preview() {
    Banner(
        cover = R.drawable.psychonauts,
        banner = R.drawable.psychonauts_banner_1,
        title = "Psychonauts 2",
        subtitle = "Double Fine Productions",
    )
}
