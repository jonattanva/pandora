package com.monolieta.pandora.android.ui.state

import com.monolieta.pandora.android.R
import com.monolieta.pandora.util.isCodeValid

class CodeState : InputState(
    validator = ::isCodeValid,
    errorFor = ::codeValidationError
)

private fun codeValidationError(): Int = R.string.invalid_code