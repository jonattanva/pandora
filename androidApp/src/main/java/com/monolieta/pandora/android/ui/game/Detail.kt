package com.monolieta.pandora.android.ui.game

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.monolieta.pandora.database.Game

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun Preview() {
    Game(
        key = "ea33b427-7483-4be4-a2f9-ac9cc4426cb1",
        release = 1632459600000,
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
        lastUpdate = null
    )
}