package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.View
import com.monolieta.pandora.android.ui.theme.PandoraTheme

@Composable
fun Avatar(navigation: NavHostController, modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Filled.AccountCircle,
        contentDescription = "",
        modifier = modifier.size(32.dp).clickable {
            navigation.navigate(View.Login.route)
        }
    )
}

@Composable
@Preview(name = "Dark Mode")
fun AvatarDarkMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = true) {
        Avatar(navigation = navController)
    }
}

@Composable
@Preview(name = "Light Mode")
fun AvatarLightMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = false) {
        Avatar(navigation = navController)
    }
}