package com.monolieta.pandora.android.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.ui.Screen

@Composable
fun Navigation(navController: NavHostController, items: Array<Screen>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(screen.icon, contentDescription = null)
                },

                label = {
                    Text(text = stringResource(id = screen.title))
                },

                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                onClick = {
                    navController.navigate(screen.route) {
                        restoreState = true
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    Navigation(navController = rememberNavController(), items = Screen.values())
}