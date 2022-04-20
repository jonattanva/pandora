package com.monolieta.pandora.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.ui.authentication.VerificationView
import com.monolieta.pandora.android.ui.authentication.RegisterView
import com.monolieta.pandora.android.ui.authentication.LoginView
import com.monolieta.pandora.android.ui.authentication.RecoverView
import com.monolieta.pandora.android.ui.home.HomeView
import com.monolieta.pandora.model.User

sealed class View(val route: String) {
    object Home : View("home")
    object Login : View("login")
    object Account : View("account")
    object Recover : View("recover")
    object Confirm : View("confirm")
}

@Composable
fun PandoraApp(startDestination: String = View.Home.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = View.Home.route) {
            HomeView(navigation = navController)
        }

        composable(route = View.Login.route) {
            LoginView(navigation = navController)
        }

        composable(route = View.Account.route) {
            RegisterView(navigation = navController)
        }

        composable(route = "${View.Confirm.route}/{id}/{email}") { backStackEntry ->
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

        composable(route = View.Recover.route) {
            RecoverView(navigation = navController)
        }
    }
}