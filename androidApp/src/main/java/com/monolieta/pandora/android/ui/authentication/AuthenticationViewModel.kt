package com.monolieta.pandora.android.ui.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.monolieta.pandora.repository.AuthenticationRepository
import androidx.lifecycle.ViewModel
import com.monolieta.pandora.Result
import com.monolieta.pandora.android.R
import com.monolieta.pandora.model.User

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    var loginResult by mutableStateOf<AuthenticationResult?>(null)
        private set

    var recoverResult by mutableStateOf<AuthenticationResult?>(null)
        private set

    var registerResult by mutableStateOf<AuthenticationResult?>(null)
        private set

    suspend fun signIn(username: String, password: String) {
        val result = authenticationRepository.signIn(username, password)
        if (result is Result.Success) {
            loginResult = null
        }
        loginResult = AuthenticationResult(error = R.string.login_failed)
    }

    suspend fun reset(username: String) {
        val result = authenticationRepository.reset(username)
        if (result is Result.Success) {
            recoverResult = null
        }
        recoverResult = AuthenticationResult(error = R.string.recover_failed)
    }

    suspend fun signUp(user: User, confirm: String) {
        val result = authenticationRepository.signUp(user, confirm)
        if (result is Result.Success) {
            registerResult = null
        }
        registerResult = AuthenticationResult(error = R.string.login_failed)
    }
}