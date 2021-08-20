package com.monolieta.pandora.android.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Games
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Navigation
import com.monolieta.pandora.android.ui.theme.PandoraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PandoraTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainView()
                }
            }
        }
    }
}

@Composable
private fun MainView() {
    val navController = rememberNavController()
    MainContent(navController) {
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                HomeView()
            }
        }
    }
}

@Composable
private fun MainContent(navController: NavHostController, content: @Composable () -> Unit) {
    Scaffold(bottomBar = { Navigation(navController, Screen.values()) }) {
        content()
    }
}

enum class Screen(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    Home(R.string.home, icon = Outlined.Home, route = "home/main"),
    Search(R.string.search, icon = Outlined.Search, route = "home/search"),
    Library(R.string.library, icon = Outlined.Games, route = "home/library")
}