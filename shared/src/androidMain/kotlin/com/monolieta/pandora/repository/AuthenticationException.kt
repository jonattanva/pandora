package com.monolieta.pandora.repository

import java.io.IOException

open class AuthenticationException(val code: String, override val message: String) :
    IOException(message) {

    companion object {
        const val USERNAME_EXISTS_EXCEPTION = "UsernameExistsException"
        const val INVALID_PASSWORD_EXCEPTION = "InvalidPasswordException"
        const val INVALID_EMAIL_EXCEPTION = "InvalidEmailException"

        val UsernameExistsException = AuthenticationException(
            USERNAME_EXISTS_EXCEPTION,
            "Username already exists in the system"
        )

        val InvalidPasswordException = AuthenticationException(
            INVALID_PASSWORD_EXCEPTION,
            "Invalid password"
        )

        val InvalidEmailException = AuthenticationException(
            INVALID_EMAIL_EXCEPTION,
            "Invalid email"
        )
    }
}