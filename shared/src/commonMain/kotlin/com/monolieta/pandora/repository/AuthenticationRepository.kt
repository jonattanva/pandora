package com.monolieta.pandora.repository

import com.monolieta.pandora.extra.Result
import com.monolieta.pandora.model.User

expect class AuthenticationRepository() {
    suspend fun signIn(
        username: String,
        password: String
    ): Result<User>

    suspend fun reset(username: String): Result<Boolean>
    suspend fun signUp(user: User): Result<User>
}