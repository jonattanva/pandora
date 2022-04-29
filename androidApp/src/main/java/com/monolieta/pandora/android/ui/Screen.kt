package com.monolieta.pandora.android.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Account : Screen("account")
    object Recover : Screen("recover")
    object Verification : Screen("verification")
}