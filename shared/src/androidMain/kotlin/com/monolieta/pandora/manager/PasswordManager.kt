package com.monolieta.pandora.manager

import javax.crypto.Cipher
import javax.crypto.KeyGenerator

actual class PasswordManager {

    actual fun encrypt(password: String): String {
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(256)

        val key = keyGenerator.generateKey()
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, key)

        // TODO: PENDIENTE VALIDAR
        val result = cipher.doFinal(password.toByteArray())
        return password
    }

    private fun decrypt(encrypt: String): String {
        return encrypt
    }

    actual fun check(encrypt: String, password: String): Boolean {
        return decrypt(encrypt) == password
    }
}