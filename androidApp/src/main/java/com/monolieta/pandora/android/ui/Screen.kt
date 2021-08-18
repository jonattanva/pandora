package com.monolieta.pandora.android.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Games
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.home.HomeView
import com.monolieta.pandora.android.ui.library.LibraryView
import com.monolieta.pandora.android.ui.search.SearchView

enum class Screen(
    @StringRes val title: Int, val icon: ImageVector, val route: String
) {
    Home(R.string.home, Icons.Outlined.Home, "home"),
    Search(R.string.search, Icons.Outlined.Search, "home/search"),
    Library(R.string.my_library, Icons.Outlined.Games, "home/library")
}

@Composable
fun ScreenHandle(navController: NavHostController, padding: PaddingValues) {
    val modifier = Modifier.padding(padding)

    NavHost(navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            HomeView()
        }

        composable(Screen.Search.route) {
            SearchView()
        }

        composable(Screen.Library.route) {
            LibraryView()
        }
    }
}
