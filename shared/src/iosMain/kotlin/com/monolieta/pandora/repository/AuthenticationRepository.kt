package com.monolieta.pandora.repository

import com.monolieta.pandora.util.Result
import com.monolieta.pandora.model.User

actual class AuthenticationRepository actual constructor(
    private val repository: UserRepository
) {
    actual suspend fun signIn(
        username: String,
        password: String
    ): Result<User> {
        TODO("Not yet implemented")
    }

    actual suspend fun resetPassword(username: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

    actual suspend fun signUp(user: User): Result<User> {
        TODO("Not yet implemented")
    }

    actual suspend fun confirmSignUp(
        user: User,
        code: String
    ): Result<User> {
        TODO("Not yet implemented")
    }

    actual suspend fun resendSignUpCode(username: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

}