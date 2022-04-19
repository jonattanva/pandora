package com.monolieta.pandora.android.ui.state

import android.util.Patterns

class EmailState : InputState(
    validator = ::isEmailValid,
    errorFor = ::emailValidationError
)

private fun isEmailValid(email: String): Boolean =
    email.contains('@') && Patterns.EMAIL_ADDRESS.matcher(email).matches()


@Suppress("UNUSED_PARAMETER")
private fun emailValidationError(email: String): String = "Invalid email"