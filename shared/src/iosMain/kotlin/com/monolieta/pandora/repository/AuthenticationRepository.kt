package com.monolieta.pandora.repository

import com.monolieta.pandora.extra.Result
import com.monolieta.pandora.model.User

actual class AuthenticationRepository actual constructor() {
    actual suspend fun signIn(username: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }

    actual suspend fun reset(username: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

    actual suspend fun signUp(user: User): Result<User> {
        TODO("Not yet implemented")
    }
}