package com.monolieta.pandora.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.ui.authentication.RegisterView
import com.monolieta.pandora.android.ui.authentication.LoginView
import com.monolieta.pandora.android.ui.authentication.RecoverView

sealed class View(val route: String) {
    object Login : View("login")
    object Account : View("account")
    object Recover : View("recover")
}

@Composable
fun PandoraApp(startDestination: String = View.Login.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = View.Account.route) {
            RegisterView(navigation = navController)
        }
        composable(route = View.Login.route) {
            LoginView(navigation = navController)
        }
        composable(route = View.Recover.route) {
            RecoverView(navigation = navController)
        }
    }
}