package com.monolieta.pandora.android.ui.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.monolieta.pandora.repository.AuthenticationRepository
import androidx.lifecycle.ViewModel
import com.monolieta.pandora.util.Result
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.Screen
import com.monolieta.pandora.model.User
import com.monolieta.pandora.repository.AuthenticationException
import com.monolieta.pandora.repository.AuthenticationException.Companion.INVALID_EMAIL_EXCEPTION
import com.monolieta.pandora.repository.AuthenticationException.Companion.INVALID_PASSWORD_EXCEPTION
import com.monolieta.pandora.repository.AuthenticationException.Companion.USERNAME_EXISTS_EXCEPTION
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    var loading by mutableStateOf(false)
        private set

    var authenticationResult by mutableStateOf<AuthenticationResult?>(null)
        private set

    suspend fun signIn(username: String, password: String) {
        if (loading) {
            return
        }

        loading = true
        authenticationResult = null

        val result = repository.signIn(username, password)
        if (result is Result.Success) {
            loading = false
            authenticationResult = AuthenticationResult(route = Screen.Home.route)
            return
        }

        loading = false
        val exception = getException(result)
        if (exception?.code == AuthenticationException.USER_NOT_CONFIRMED_EXCEPTION) {
            authenticationResult = AuthenticationResult(
                route = "${Screen.Confirm.route}/ /${username}"
            )
            return
        }

        authenticationResult = AuthenticationResult(
            error = getError(result, R.string.login_failed)
        )
    }

    suspend fun resendSignUpCode(username: String) {
        val result = repository.resendSignUpCode(username)
        if (result is Result.Success) {

        }

        authenticationResult = AuthenticationResult(
            error = getError(result, R.string.confirm_sign_in_failed)
        )
    }

    suspend fun confirmSignUp(user: User, code: String) {
        if (loading) {
            return
        }

        loading = true
        authenticationResult = null

        val result = repository.confirmSignUp(user.email, code)
        if (result is Result.Success) {
            loading = false
            authenticationResult = AuthenticationResult(route = Screen.Home.route)
            return
        }

        loading = false
        authenticationResult = AuthenticationResult(
            error = getError(result, R.string.confirm_sign_in_failed)
        )
    }

    suspend fun resetPassword(username: String) {
        if (loading) {
            return
        }

        loading = true
        authenticationResult = null

        val result = repository.resetPassword(username)
        if (result is Result.Success) {
            loading = false
            authenticationResult = null
            return
        }

        loading = false
        authenticationResult = AuthenticationResult(
            error = getError(result, R.string.recover_failed)
        )
    }

    suspend fun signUp(user: User) {
        if (loading) {
            return
        }

        loading = true
        authenticationResult = null

        val result = repository.signUp(user)
        if (result is Result.Success) {
            loading = false
            authenticationResult = AuthenticationResult(
                route = "${Screen.Confirm.route}/${result.data.id}/${result.data.email}"
            )
            return
        }

        loading = false
        authenticationResult = AuthenticationResult(
            error = getError(result, R.string.sign_up_failed)
        )
    }

    fun clear() {
        authenticationResult = null
    }

    private fun <T : Any> getException(result: Result<T>): AuthenticationException? {
        if (result is Result.Error) {
            val exception = result.exception
            if (exception is AuthenticationException) {
                return exception
            }
        }
        return null
    }

    private fun <T : Any> getError(result: Result<T>, defaultMessage: Int): Int {
        return getException(result)?.let {
            getMessage(it.code)
        } ?: defaultMessage
    }

    private fun getMessage(code: String): Int? {
        return when (code) {
            INVALID_EMAIL_EXCEPTION -> R.string.invalid_email
            INVALID_PASSWORD_EXCEPTION -> R.string.invalid_password
            USERNAME_EXISTS_EXCEPTION -> R.string.account_already_exists
            else -> null
        }
    }
}