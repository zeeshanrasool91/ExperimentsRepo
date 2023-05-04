package com.example.myapplication

import android.util.Base64
import android.util.Log

fun Any.decryptIfValid(): String {
    val enc = this.toString()
    Log.d(TAG, "decryptIfValid 1: ${enc.isValidBase64()}")
    Log.d(TAG, "decryptIfValid 2: ${enc.isValidBase64Str()}")
    Log.d(TAG, "decryptIfValid 3: ${enc.isBase64()}")
    Log.d(TAG, "decryptIfValid 4: ${enc.fromBase64()}")
    return enc
}

fun String.isBase64(): Boolean {
    try {
        val decoded = this.decodeFromBase64()
        when {
            decoded.isEmpty() -> {
                return false
            }

            decoded.equals(this, ignoreCase = true) -> {
                return true
            }
        }
    } catch (ignored: Exception) {
    }
    return false
}

fun String.fromBase64(): ByteArray? = try {
    Base64.decode(this, Base64.NO_WRAP or Base64.URL_SAFE)
} catch (e: Exception) {
    null
}


private fun String.isValidBase64Str(): Boolean {
    val regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$"
    return this.matches(regex.toRegex()) && !this.containsSpaces()
}

fun String.containsSpaces(): Boolean {
    return this.contains(" ") ||
            this.contains("\n") ||
            this.contains("\r") ||
            this.contains("\t")
}