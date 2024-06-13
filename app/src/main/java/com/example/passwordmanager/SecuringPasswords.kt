package com.example.passwordmanager

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.nio.charset.StandardCharsets
import android.util.Base64

object EncryptionUtil {

    private const val PREFERENCE_FILE = "encrypted_prefs"
    private const val KEY_PASSWORD = "password"

    fun encrypt(context: Context, data: String): String {
        val masterKeyAlias = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val encryptedSharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFERENCE_FILE,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val encryptedData = Base64.encodeToString(data.toByteArray(StandardCharsets.UTF_8), Base64.DEFAULT)
        encryptedSharedPreferences.edit().putString(KEY_PASSWORD, encryptedData).apply()
        return encryptedData
    }

    fun decrypt(context: Context): String {
        val masterKeyAlias = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val encryptedSharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFERENCE_FILE,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val encryptedData = encryptedSharedPreferences.getString(KEY_PASSWORD, "") ?: ""
        return String(Base64.decode(encryptedData, Base64.DEFAULT), StandardCharsets.UTF_8)
    }
}
