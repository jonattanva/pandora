package com.monolieta.pandora.repository

import com.monolieta.pandora.util.Result
import com.monolieta.pandora.model.User
import com.pandora.database.UserQueries

expect class AuthenticationRepository(
    repository: UserRepository
) {
    suspend fun signIn(
        username: String,
        password: String
    ): Result<User>

    suspend fun resetPassword(username: String): Result<Boolean>
    suspend fun signUp(user: User): Result<User>

    suspend fun confirmSignUp(
        user: User,
        code: String
    ): Result<User>

    suspend fun resendSignUpCode(username: String): Result<Boolean>
}