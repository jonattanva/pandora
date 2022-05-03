package com.monolieta.pandora.util

fun isCodeValid(code: String): Boolean = code.length == 6

fun isPasswordValid(password: String): Boolean = password.length > 7

fun isPasswordAndConfirmationValid(password: String, confirmedPassword: String): Boolean =
    isPasswordValid(password) && password == confirmedPassword

fun isEmailValid(email: String): Boolean = email.contains('@') && EMAIL_ADDRESS.matches(email)

private val EMAIL_ADDRESS =
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+".toRegex()