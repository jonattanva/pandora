package com.monolieta.pandora.repository

import com.monolieta.pandora.util.Result
import com.monolieta.pandora.model.User
import com.monolieta.pandora.manager.PasswordManager

expect class AuthenticationRepository(
    repository: UserRepository,
    passwordManager: PasswordManager
) {
    suspend fun signIn(
        username: String,
        password: String
    ): Result<User>

    suspend fun resetPassword(username: String): Result<Boolean>
    suspend fun signUp(user: User): Result<User>

    suspend fun confirmSignUp(
        username: String,
        code: String
    ): Result<User>

    suspend fun resendSignUpCode(username: String): Result<Boolean>
}