package com.monolieta.pandora.android.ui.state

import com.monolieta.pandora.android.R
import com.monolieta.pandora.util.isEmailValid

class EmailState : InputState(
    validator = ::isEmailValid,
    errorFor = ::emailValidationError
)

private fun emailValidationError(): Int = R.string.invalid_email