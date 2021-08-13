package com.monolieta.pandora

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}