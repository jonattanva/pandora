package com.monolieta.pandora.repository

import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.kotlin.core.Amplify
import com.monolieta.pandora.manager.KeyStoreManager
import com.monolieta.pandora.util.Result
import com.monolieta.pandora.util.isEmailValid
import com.monolieta.pandora.util.isPasswordValid
import com.monolieta.pandora.model.User
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import java.io.IOException

actual class AuthenticationRepository actual constructor(
    private val repository: UserRepository,
    private val keyStoreManager: KeyStoreManager
) {
    actual suspend fun signIn(username: String, password: String): Result<User> {
        try {
            if (username.isEmpty()) {
                return Result.Error(AuthenticationException.InvalidEmailException)
            }

            if (password.isEmpty()) {
                return Result.Error(AuthenticationException.InvalidPasswordException)
            }

            val current = repository.findByEmail(username)
                .filter { check(it, password) }
                .firstOrNull()

            if (current != null) {
                return Result.Success(current.copy(password = ""))
            }

            val result = Amplify.Auth.signIn(username, password)
            if (result.isSignInComplete) {
                Amplify.Auth.getCurrentUser()?.let {
                    return save(
                        User(
                            id = it.userId,
                            email = it.username,
                            password = keyStoreManager.encrypt(password, it.userId),
                            verified = true
                        )
                    )
                }
            }

            throw IOException("Sign in not complete")
        } catch (error: Exception) {
            if (error is AuthException.UserNotConfirmedException) {
                return Result.Error(AuthenticationException.UserNotConfirmedException)
            }
            return Result.Error(IOException("Error sign in", error))
        }
    }

    actual suspend fun resetPassword(username: String): Result<Boolean> {
        try {
            if (username.isEmpty()) {
                return Result.Error(AuthenticationException.InvalidEmailException)
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

    actual suspend fun signUp(user: User): Result<User> {
        try {
            if (!isEmailValid(user.email)) {
                return Result.Error(AuthenticationException.InvalidEmailException)
            }

            if (!isPasswordValid(user.password)) {
                return Result.Error(AuthenticationException.InvalidPasswordException)
            }

            val exists = repository.exists(user.email)
                .first()

            if (exists) {
                return Result.Error(AuthenticationException.UsernameExistsException)
            }

            val options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), user.email)
                .build()

            val result = Amplify.Auth.signUp(user.email, user.password, options)
            if (result.isSignUpComplete) {
                result.user?.let { it ->
                    return save(
                        user.copy(
                            id = it.userId,
                            password = keyStoreManager.encrypt(user.password, it.userId)
                        )
                    )
                }
            }

            throw IOException("Sign up not complete")
        } catch (error: Exception) {
            if (error is AuthException.UsernameExistsException) {
                return Result.Error(AuthenticationException.UsernameExistsException)
            }
            return Result.Error(IOException("Sign up failed", error))
        }
    }

    @OptIn(FlowPreview::class)
    actual suspend fun confirmSignUp(username: String, code: String): Result<User> {
        try {
            if (!isEmailValid(username)) {
                return Result.Error(AuthenticationException.InvalidEmailException)
            }

            if (code.isEmpty()) {
                return Result.Error(AuthenticationException.InvalidCodeException)
            }

            val result = Amplify.Auth.confirmSignUp(username, code)
            if (result.isSignUpComplete) {
                return Result.Success(repository.findByEmail(username)
                    .flatMapConcat { repository.save(it.copy(verified = true)) }
                    .map { it.copy(password = "") }
                    .first())
            }

            throw IOException("Confirm sign up not complete")
        } catch (error: Exception) {
            return Result.Error(IOException("Failed to confirm sign up", error))
        }
    }

    actual suspend fun resendSignUpCode(username: String): Result<Boolean> {
        try {
            if (username.isEmpty()) {
                return Result.Error(AuthenticationException.InvalidEmailException)
            }

            val result = Amplify.Auth.resendSignUpCode(username)
            if (result.isSignUpComplete) {
                return Result.Success(true)
            }

            throw IOException("Resend sign up code not complete")
        } catch (error: Exception) {
            return Result.Error(IOException("Resend sign up code failed", error))
        }
    }

    private suspend fun save(user: User): Result.Success<User> {
        return Result.Success(
            repository.save(user).map { result ->
                result.copy(password = "")
            }.first()
        )
    }

    private fun check(user: User, password: String) =
        user.id != null && user.verified && keyStoreManager.check(user.password, user.id, password)
}