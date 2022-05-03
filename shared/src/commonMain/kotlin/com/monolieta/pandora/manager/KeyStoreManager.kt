package com.monolieta.pandora.manager

interface KeyStoreManager {
    fun encrypt(password: String, alias: String): String
    fun check(encrypted: String, alias: String, password: String): Boolean
}