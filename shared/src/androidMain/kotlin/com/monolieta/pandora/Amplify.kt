package com.monolieta.pandora

import android.content.Context
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.AmplifyConfiguration
import com.amplifyframework.kotlin.core.Amplify
import org.json.JSONObject

actual class Amplify(private val context: Context) {

    actual fun init() {
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.configure(context)
    }
}