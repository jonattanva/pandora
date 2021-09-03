package com.monolieta.pandora.android.ui.game

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.DateView
import com.monolieta.pandora.android.ui.component.Tab
import com.monolieta.pandora.database.Game

@Composable
fun Detail(@DrawableRes banner: Int, @DrawableRes cover: Int, game: Game) {
    Detail(game = game) {
        Banner(
            banner = banner,
            cover = cover,
            title = game.name,
            subtitle = game.developer
        )
    }
}

@Composable
fun Detail(game: Game) {
    Detail(game = game) {
        Banner(
            banner = game.screenshots.random(),
            cover = game.cover,
            title = game.name,
            subtitle = game.developer
        )
    }
}

@Composable
private fun Detail(game: Game, content: @Composable () -> Unit) {
    Column(modifier = Modifier.background(Color.Black)) {
        content()

        Spacer(modifier = Modifier.height(4.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = game.description,
                color = Color.LightGray,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.release_date),
                color = Color.White,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(4.dp))

            DateView(
                timestamp = game.release,
                color = Color.LightGray,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.genres),
                color = Color.LightGray,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Tab(
                values = game.genre,
                color = Color.DarkGray,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.platforms),
                color = Color.LightGray,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Tab(
                values = game.platforms,
                color = Color.DarkGray,
                style = MaterialTheme.typography.body2
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
    Detail(
        banner = R.drawable.psychonauts_banner,
        cover = R.drawable.psychonauts,
        game = Game(
            key = "ea33b427-7483-4be4-a2f9-ac9cc4426cb1",
            release = 1632459600000,
            cover = "ea33b427-7483-4be4-a2f9-ac9cc4426cb1.webp",
            name = "Psychonauts 2",
            description = "Psychonauts 2 is a mind-bending trip through the strange worlds " +
                    "hiding inside our brains. Freshly-minted special agent and acrobat " +
                    "extraordinaire Razputin “Raz” Aquato returns to unpack emotional baggage " +
                    "and expand mental horizons. Along the way he’ll help new friends, like " +
                    "this magical mote of light voiced (and sung) by Jack Black. Raz must " +
                    "use his powers to unravel dark mysteries about the Psychonauts team " +
                    "and his own family origins.",
            developer = "Double Fine Productions",
            platforms = listOf("PlayStation 4", "Xbox One"),
            genre = listOf("Adventure", "Platform"),
            screenshots = listOf("ea33b427-7483-4be4-a2f9-ac9cc4426cb1-1.webp"),
            lastUpdate = null
        )
    )
}