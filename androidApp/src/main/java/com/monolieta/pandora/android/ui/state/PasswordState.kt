package com.monolieta.pandora.android.ui.state

import com.monolieta.pandora.android.R
import com.monolieta.pandora.util.isPasswordAndConfirmationValid
import com.monolieta.pandora.util.isPasswordValid

class PasswordState : InputState(
    validator = ::isPasswordValid,
    errorFor = ::passwordValidationError
)

class ConfirmPasswordState(private val passwordState: PasswordState) : InputState() {

    override val isValid
        get() = isPasswordAndConfirmationValid(passwordState.value, value)

    override fun error(): Int? {
        return if (isError()) {
            passwordConfirmationError()
        } else null
    }
}

private fun passwordValidationError(): Int = R.string.invalid_password
private fun passwordConfirmationError(): Int = R.string.passwords_do_not_match