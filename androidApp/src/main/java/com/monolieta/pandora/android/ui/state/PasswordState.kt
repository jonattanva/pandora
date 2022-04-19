package com.monolieta.pandora.android.ui.state

class PasswordState : InputState(
    validator = ::isPasswordValid,
    errorFor = ::passwordValidationError
)

class ConfirmPasswordState(private val passwordState: PasswordState) : InputState() {
    override val isValid
        get() = passwordAndConfirmationValid(passwordState.value, value)

    override fun error(): String? {
        return if (isError()) {
            passwordConfirmationError()
        } else null
    }
}

private fun isPasswordValid(password: String): Boolean = password.length > 7

private fun passwordAndConfirmationValid(password: String, confirmedPassword: String): Boolean =
    isPasswordValid(password) && password == confirmedPassword

@Suppress("UNUSED_PARAMETER")
private fun passwordValidationError(password: String): String = "Invalid password"

private fun passwordConfirmationError(): String = "Passwords don't match"