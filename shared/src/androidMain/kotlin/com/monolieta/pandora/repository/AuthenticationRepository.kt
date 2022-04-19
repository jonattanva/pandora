package com.monolieta.pandora.repository

import android.util.Patterns
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.kotlin.core.Amplify
import com.monolieta.pandora.Result
import com.monolieta.pandora.model.User
import java.io.IOException

actual class AuthenticationRepository actual constructor() {

    actual suspend fun signIn(username: String, password: String): Result<User> {
        try {
            if (username.isEmpty()) {
                throw IOException("The username is required")
            }

            if (password.isEmpty()) {
                throw IOException("The password is required")
            }

            val result = Amplify.Auth.signIn(username, password)
            if (result.isSignInComplete) {
                val current = Amplify.Auth.getCurrentUser()
                if (current != null) {
                    return Result.Success(
                        User(
                            id = current.userId,
                            email = current.username,
                            password = ""
                        )
                    )
                }
            }

            throw IOException("Sign in not complete")
        } catch (error: Exception) {
            return Result.Error(IOException("Error sign in", error))
        }
    }

    actual suspend fun reset(username: String): Result<Boolean> {
        try {
            if (username.isEmpty()) {
                throw IOException("The username is required")
            }

            val result = Amplify.Auth.resetPassword(username)
            if (result.isPasswordReset) {
                return Result.Success(true)
            }

            throw IOException("Password reset not complete")
        } catch (error: Exception) {
            return Result.Error(IOException("Password reset failed", error))
        }
    }

    actual suspend fun signUp(user: User, confirm: String): Result<User> {
        try {
            /*
            val email = user.email.trim()
            val username = user.username.trim()
            val password = user.password.trim()

            if (email.isEmpty()) {
                throw IOException("The email is required")
            }

            if (!isUserNameValid(username)) {
                throw IOException("The username is required")
            }

            if (!isPasswordValid(password)) {
                throw IOException("The password is required")
            }

            if (password != confirm) {
                throw IOException("Those passwords didn't match.")
            }

            val options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), email)
                .build()

            val result = Amplify.Auth.signUp(username, password, options)
            if (result.isSignUpComplete) {
                result.user?.let {
                    return Result.Success(
                        user.copy(
                            id = it.userId,
                            username = ,
                            password = "",
                            email = email
                        )
                    )
                }
            }*/

            throw IOException("Sign up not complete")
        } catch (error: Exception) {
            return Result.Error(IOException("Sign up failed", error))
        }
    }

    private fun isUserNameValid(username: String): Boolean =
        username.contains('@') && Patterns.EMAIL_ADDRESS.matcher(username).matches()

    private fun isPasswordValid(password: String): Boolean = password.length > 7
}