package com.monolieta.pandora.manager

expect class PasswordManager : KeyStoreManager {
    override fun encrypt(password: String, alias: String): String
    override fun check(encrypted: String, alias: String, password: String): Boolean
}