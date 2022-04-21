package com.monolieta.pandora.android.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.ui.authentication.*
import com.monolieta.pandora.android.ui.home.HomeView
import com.monolieta.pandora.model.User

@Composable
fun NavigationView(startDestination: String = Screen.Home.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Screen.Home.route) {
            HomeView(navigation = navController)
        }

        composable(route = Screen.Login.route) {
            val authenticationViewModel = hiltViewModel<AuthenticationViewModel>()
            LoginView(
                navigation = navController,
                viewModel = authenticationViewModel
            )
        }

        composable(route = Screen.Account.route) {
            RegisterView(navigation = navController)
        }

        composable(route = "${Screen.Confirm.route}/{id}/{email}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id", "")
            val email = backStackEntry.arguments?.getString("email", "") ?: ""

            VerificationView(
                navigation = navController,
                user = User(
                    id = id?.trim(),
                    email = email
                )
            )
        }

        composable(route = Screen.Recover.route) {
            RecoverView(navigation = navController)
        }
    }
}