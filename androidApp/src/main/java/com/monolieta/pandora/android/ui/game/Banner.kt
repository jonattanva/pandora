package com.monolieta.pandora.android.ui.game

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.res.ResourcesCompat
import androidx.palette.graphics.Palette
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Cover
import com.monolieta.pandora.android.ui.component.Information

@Composable
fun Banner(@DrawableRes banner: Int, @DrawableRes cover: Int, title: String, subtitle: String) {
    val color = getVibrantColor(
        bitmap = ImageBitmap.imageResource(banner)
            .asAndroidBitmap()
    )

    Column(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(color, Color.Black)
                )
            )
    ) {
        Cover(
            painter = painterResource(banner),
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Information(cover = cover, title = title, subtitle = subtitle)

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

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun Preview() {
    Banner(
        cover = R.drawable.psychonauts,
        banner = R.drawable.psychonauts_banner,
        title = "Psychonauts 2",
        subtitle = "Double Fine Productions",
    )
}

// Julieta Velásquez Álvarez 6 years old