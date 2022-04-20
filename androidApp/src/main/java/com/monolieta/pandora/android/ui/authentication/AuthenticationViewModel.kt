package com.monolieta.pandora.android.ui.authentication

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.monolieta.pandora.repository.AuthenticationRepository
import androidx.lifecycle.ViewModel
import com.monolieta.pandora.extra.Result
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.View
import com.monolieta.pandora.model.User
import com.monolieta.pandora.repository.AuthenticationException
import com.monolieta.pandora.repository.AuthenticationException.Companion.CONFIRM_SIGN_IN_WITH_NEW_PASSWORD
import com.monolieta.pandora.repository.AuthenticationException.Companion.INVALID_EMAIL_EXCEPTION
import com.monolieta.pandora.repository.AuthenticationException.Companion.INVALID_PASSWORD_EXCEPTION
import com.monolieta.pandora.repository.AuthenticationException.Companion.USERNAME_EXISTS_EXCEPTION

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    var recoverResult by mutableStateOf<AuthenticationResult?>(null)
        private set

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

        val result = authenticationRepository.signIn(username, password)
        if (result is Result.Success) {
            loading = false
            authenticationResult = AuthenticationResult(route = View.Home.route)
            return
        }

        if (getException(result)?.code == CONFIRM_SIGN_IN_WITH_NEW_PASSWORD) {
            loading = false
            authenticationResult = AuthenticationResult(
                route = "${View.Confirm.route}/${username}"
            )
            return
        }

        loading = false
        authenticationResult = AuthenticationResult(error = getError(result, R.string.login_failed))
    }

    suspend fun resendSignUpCode(username: String) {
        Log.e("TAG", "resendSignUpCode: " + username)
        val result = authenticationRepository.resendSignUpCode(username)
        Log.e("TAG", "resendSignUpCode: " + result)
    }

    suspend fun confirmSignUp(username: String, code: String) {
        if (loading) {
            return
        }

        loading = true
        authenticationResult = null

        val result = authenticationRepository.confirmSignUp(username, code)
        if (result is Result.Success) {
            loading = false
            authenticationResult = AuthenticationResult(route = View.Home.route)
            return
        }

        loading = false
        authenticationResult = AuthenticationResult(
            error = getError(result, R.string.confirm_sign_in_failed)
        )
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
        authenticationResult = null

        val result = authenticationRepository.signUp(user)
        if (result is Result.Success) {
            loading = false
            authenticationResult = AuthenticationResult(
                route = "${View.Confirm.route}/${user.email}"
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