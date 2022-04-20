package com.monolieta.pandora.model

data class User(
    val id: String? = null,
    val email: String,
    val password: String = "",
    val verified: Boolean = false
)