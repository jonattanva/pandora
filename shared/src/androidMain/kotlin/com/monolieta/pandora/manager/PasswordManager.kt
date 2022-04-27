package com.monolieta.pandora.manager

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

actual class PasswordManager : KeyStoreManager {

    companion object {
        private const val SEPARATOR = ","
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
        private const val TRANSFORMATION =
            "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/${KeyProperties.ENCRYPTION_PADDING_PKCS7}"
    }

    actual override fun encrypt(password: String, alias: String): String {
        val secretKey = generateKey(alias)

        val cipher = Cipher.getInstance(TRANSFORMATION)
            .apply { init(Cipher.ENCRYPT_MODE, secretKey) }

        val iv = Base64.encodeToString(cipher.iv, Base64.DEFAULT)
        val encrypted = Base64.encodeToString(
            cipher.doFinal(password.toByteArray()),
            Base64.DEFAULT
        )

        return iv + SEPARATOR + encrypted
    }

    actual override fun check(encrypted: String, alias: String, password: String): Boolean {
        try {
            val result = decrypt(encrypted, alias)
                ?: return false
            return result == password
        } catch (e: Exception) {
            return false
        }
    }

    private fun decrypt(value: String, alias: String): String? {
        val parts = value.split(SEPARATOR)
            .toTypedArray()

        if (parts.size != 2) {
            return null
        }

        val iv = Base64.decode(parts[0], Base64.DEFAULT)
        val encrypted = Base64.decode(parts[1], Base64.DEFAULT)
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE).apply {
            load(null)
        }

        val secretKey: SecretKey?
        val entry = keyStore.getEntry(alias, null)
        if (entry is KeyStore.SecretKeyEntry) {
            secretKey = entry.secretKey
        } else return null

        val spec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance(TRANSFORMATION)
            .apply { init(Cipher.DECRYPT_MODE, secretKey, spec) }

        return String(cipher.doFinal(encrypted), StandardCharsets.UTF_8)
    }

    private fun generateKey(alias: String): SecretKey? {
        val purposes = KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        val params = KeyGenParameterSpec.Builder(alias, purposes)
            .apply {
                setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            }.build()

        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE
        ).apply { init(params) }

        return keyGenerator.generateKey()
    }
}