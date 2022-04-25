package com.monolieta.pandora.android

import com.monolieta.pandora.Amplify

object MainAmplify {

    var configured = false
        private set

    fun init(amplify: Amplify) {
        if (!configured) {
            configured = true
            amplify.init()
        }
    }
}