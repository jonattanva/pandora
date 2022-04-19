package com.monolieta.pandora.android.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monolieta.pandora.repository.AuthenticationRepository

class AuthenticationViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java)) {
            return AuthenticationViewModel(
                authenticationRepository = AuthenticationRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}