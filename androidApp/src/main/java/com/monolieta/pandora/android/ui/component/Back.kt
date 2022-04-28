package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.ui.BACK_TAG
import com.monolieta.pandora.android.ui.theme.PandoraTheme

@Composable
fun Back(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = "",
        modifier = modifier
            .size(32.dp)
            .clickable { }
            .testTag(BACK_TAG),
    )
}

@Composable
@Preview(name = "Dark Mode")
fun BackDarkMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = true) {
        Back()
    }
}

@Composable
@Preview(name = "Light Mode")
fun BackLightMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = false) {
        Back()
    }
}