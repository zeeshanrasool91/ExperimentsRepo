package com.example.myapplication

import android.util.Base64
import android.util.Log
import java.io.UnsupportedEncodingException

object JWTUtils {
    @Throws(Exception::class)
    fun decoded(jwtToken: String) {
        try {
            val split = jwtToken.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]))
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]))
        } catch (e: UnsupportedEncodingException) {
            //Error
        }
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, Charsets.UTF_8)
    }
}