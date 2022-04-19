package com.monolieta.pandora.android.ui.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.monolieta.pandora.repository.AuthenticationRepository
import androidx.lifecycle.ViewModel
import com.monolieta.pandora.extra.Result
import com.monolieta.pandora.android.R
import com.monolieta.pandora.model.User
import com.monolieta.pandora.repository.AuthenticationException
import com.monolieta.pandora.repository.AuthenticationException.Companion.INVALID_EMAIL_EXCEPTION
import com.monolieta.pandora.repository.AuthenticationException.Companion.INVALID_PASSWORD_EXCEPTION
import com.monolieta.pandora.repository.AuthenticationException.Companion.USERNAME_EXISTS_EXCEPTION

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    var loginResult by mutableStateOf<AuthenticationResult?>(null)
        private set

    var recoverResult by mutableStateOf<AuthenticationResult?>(null)
        private set

    var registerResult by mutableStateOf<AuthenticationResult?>(null)
        private set

    var loading by mutableStateOf(false)
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

    suspend fun signUp(user: User) {
        if (loading) {
            return
        }

        loading = true
        val result = authenticationRepository.signUp(user)
        if (result is Result.Success) {
            registerResult = null
            return
        }

        val message = getError(result, R.string.sign_up_failed)
        loading = false
        registerResult = AuthenticationResult(error = message)
    }

    private fun <T : Any> getError(result: Result<T>, defaultMessage: Int): Int {
        var message = defaultMessage
        if (result is Result.Error) {
            val exception = result.exception
            if (exception is AuthenticationException) {
                getMessage(exception.code)?.let {
                    message = it
                }
            }
        }
        return message
    }

    private fun getMessage(code: String): Int? {
        return when (code) {
            INVALID_EMAIL_EXCEPTION -> R.string.invalid_email
            USERNAME_EXISTS_EXCEPTION -> R.string.account_already_exists
            INVALID_PASSWORD_EXCEPTION -> R.string.invalid_password
            else -> null
        }
    }
}