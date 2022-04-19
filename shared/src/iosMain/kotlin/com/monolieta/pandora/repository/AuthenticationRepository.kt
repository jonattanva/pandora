package com.monolieta.pandora.repository

import com.monolieta.pandora.Result
import com.monolieta.pandora.model.User

actual class AuthenticationRepository actual constructor() {
    actual suspend fun signIn(username: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }

    actual suspend fun reset(username: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

    actual suspend fun signUp(
        user: User,
        confirm: String
    ): Result<User> {
        TODO("Not yet implemented")
    }
}