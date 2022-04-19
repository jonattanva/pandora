package com.monolieta.pandora

import android.content.Context
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.kotlin.core.Amplify

actual class Amplify(private val context: Context) {
    actual fun init() {
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.configure(context)
    }
}