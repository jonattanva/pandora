package com.monolieta.pandora.model

import com.pandora.database.User

data class User(
    val id: String? = null,
    val email: String,
    val password: String = "",
    val verified: Boolean = false
) {
    companion object {
        fun convert(user: User): com.monolieta.pandora.model.User {
            return com.monolieta.pandora.model.User(
                id = user.id,
                email = user.email,
                password = user.password,
                verified = user.verified == true
            )
        }
    }
}