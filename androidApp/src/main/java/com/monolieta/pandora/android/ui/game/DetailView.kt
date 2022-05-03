package com.monolieta.pandora.android.ui.game

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Banner
import com.monolieta.pandora.android.ui.theme.PandoraTheme
import com.pandora.database.Game

@Composable
fun DetailView(
    viewModel: GameViewModel = viewModel()
) {
}

@Composable
private fun Detail(game: Game) {
    Detail(game = game) {
        Banner(
            banner = game.screenshots.random(),
            cover = game.cover,
            name = game.name,
            developer = game.developer
        )
    }
}

@Composable
private fun Detail(@DrawableRes banner: Int, @DrawableRes cover: Int, game: Game) {
    Detail(game = game) {
        Banner(
            banner = banner,
            cover = cover,
            name = game.name,
            developer = game.developer
        )
    }
}

@Composable
private fun Detail(game: Game, content: @Composable () -> Unit) {
    Column(modifier = Modifier.background(Color.Black)) {
        content()

        Spacer(modifier = Modifier.height(4.dp))
        Column(modifier = Modifier.padding(16.dp)) {
            Body(game)
        }
    }
}

@Composable
private fun Body(game: Game) {
    Text(
        text = game.description,
        color = Color.LightGray,
        style = MaterialTheme.typography.body2
    )
}

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true
)
private fun DetailLightMode() {
    PandoraTheme(darkTheme = false) {
        Detail(
            banner = R.drawable.psychonauts_banner_1,
            cover = R.drawable.psychonauts,
            game = Game(
                key = "ea33b427-7483-4be4-a2f9-ac9cc4426cb1",
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
                screenshots = listOf("ea33b427-7483-4be4-a2f9-ac9cc4426cb1-1.webp"),
                lastUpdate = null
            )
        )
    }
}

@Composable
@Preview(
    name = "Dark Mode",
    showBackground = true
)
private fun DetailDarkMode() {
    PandoraTheme(darkTheme = true) {
        Detail(
            banner = R.drawable.the_legend_of_zelda_breath_of_the_wild_banner,
            cover = R.drawable.the_legend_of_zelda_breath_of_the_wild,
            game = Game(
                key = "ea33b427-7483-4be4-a2f9-ac9cc4426cb1",
                cover = "ea33b427-7483-4be4-a2f9-ac9cc4426cb1.webp",
                name = "The legend of zelda breath of the wild",
                description = "Breath of the Wild is an action-adventure game set in an open world where players are tasked with exploring the kingdom of Hyrule while controlling Link. Breath of the Wild encourages nonlinear gameplay, which is illustrated by the game's lack of defined entrances or exits to areas, scant instruction given to the player, and encouragement to explore freely. Breath of the Wild introduces a consistent physics engine to the Zelda series, letting players approach problems in different ways rather than trying to find a single solution",
                developer = "Nintendo",
                screenshots = listOf("ea33b427-7483-4be4-a2f9-ac9cc4426cb1-1.webp"),
                lastUpdate = null
            )
        )
    }
}
