package com.monolieta.pandora.repository

import java.io.IOException

open class AuthenticationException(val code: String, override val message: String) :
    IOException(message) {

    companion object {
        const val USERNAME_EXISTS_EXCEPTION = "UsernameExistsException"
        const val INVALID_PASSWORD_EXCEPTION = "InvalidPasswordException"
        const val INVALID_EMAIL_EXCEPTION = "InvalidEmailException"
        const val CONFIRM_SIGN_IN_WITH_NEW_PASSWORD = "ConfirmSignInWithNewPassword"
        const val INVALID_CODE_EXCEPTION = "InvalidCodeException"

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

        val InvalidCodeException = AuthenticationException(
            INVALID_CODE_EXCEPTION,
            "Invalid code"
        )

        val ConfirmSignInWithNewPassword = AuthenticationException(
            CONFIRM_SIGN_IN_WITH_NEW_PASSWORD,
            "Confirm new password"
        )
    }
}