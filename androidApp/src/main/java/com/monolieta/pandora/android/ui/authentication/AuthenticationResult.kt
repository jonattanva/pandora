package com.monolieta.pandora.android.ui.authentication

import androidx.annotation.StringRes

data class AuthenticationResult(
    @StringRes val error: Int? = null,
    val route: String? = null
)