package com.monolieta.pandora.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Avatar
import com.monolieta.pandora.android.ui.theme.PandoraTheme

@Composable
fun HomeView(
    navigation: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(R.string.list),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Avatar(navigation = navigation)
        }
    }
}

@Composable
@Preview(name = "Dark Mode")
fun AvatarDarkMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = true) {
        HomeView(navigation = navController)
    }
}

@Composable
@Preview(name = "Light Mode")
fun AvatarLightMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = false) {
        HomeView(navigation = navController)
    }
}