package com.monolieta.pandora.android.ui.component

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
import androidx.compose.ui.unit.sp
import com.monolieta.pandora.android.ui.theme.PandoraTheme
import com.monolieta.pandora.android.ui.theme.getVibrantColor
import com.pandora.database.Game
import com.monolieta.pandora.android.R

@Composable
fun Banner(game: Game) {
    Banner(
        banner = game.screenshots.random(),
        cover = game.cover,
        name = game.name,
        developer = game.developer
    )
}

@Composable
fun Banner(@DrawableRes banner: Int, @DrawableRes cover: Int, name: String, developer: String) {
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
            name = name,
            developer = developer
        )
    }
}

@Composable
fun Banner(banner: String, cover: String, name: String, developer: String) {
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
            cover = cover,
            name = name,
            developer = developer
        )
    }
}

@Composable
private fun Banner(color: Color, content: @Composable () -> Unit) {
    Banner(
        modifier = Modifier.background(
            Brush.verticalGradient(
                listOf(
                    color,
                    Color.Black
                )
            )
        )
    ) { content() }
}

@Composable
private fun Banner(modifier: Modifier, content: @Composable () -> Unit) {
    Column(modifier) {
        content()
    }
}

@Composable
private fun Information(@DrawableRes cover: Int, name: String, developer: String) {
    Information(name, developer) {
        Cover(
            painter = painterResource(cover),
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
private fun Information(cover: String, name: String, developer: String) {
    Information(name, developer) {
        Cover(
            cover,
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
private fun Information(name: String, developer: String, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        content()

        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = name,
                fontSize = 22.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = developer,
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}


@Composable
@Preview(
    name = "Dark Mode",
    showBackground = true
)
private fun BannerDarkMode() {
    PandoraTheme(darkTheme = true) {
        Banner(
            banner = R.drawable.psychonauts_banner_1,
            cover = R.drawable.psychonauts,
            name = "Psychonauts 2",
            developer = "Double Fine Productions",
        )
    }
}

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true
)
private fun BannerLightMode() {
    PandoraTheme(darkTheme = false) {
        Banner(
            banner = R.drawable.the_legend_of_zelda_breath_of_the_wild_banner,
            cover = R.drawable.the_legend_of_zelda_breath_of_the_wild,
            name = "The legend of zelda breath of the wild",
            developer = "Nintendo",
        )
    }
}