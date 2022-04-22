package com.monolieta.pandora.manager

expect class PasswordManager {
    fun encrypt(password: String): String
    fun check(encrypt: String, password: String): Boolean
}