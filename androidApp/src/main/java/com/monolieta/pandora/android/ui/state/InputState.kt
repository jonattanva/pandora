package com.monolieta.pandora.android.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class InputState(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: () -> Int? = { null }
) {
    var value: String by mutableStateOf("")

    private var isFocused: Boolean by mutableStateOf(false)
    private var isFocusedDirty: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(value)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) {
            isFocusedDirty = true
        }
    }

    fun enableShowErrors() {
        if (isFocusedDirty) {
            displayErrors = true
        }
    }

    fun isError() = !isValid && displayErrors

    open fun error(): Int? {
        return if (isError()) {
            errorFor()
        } else null
    }
}