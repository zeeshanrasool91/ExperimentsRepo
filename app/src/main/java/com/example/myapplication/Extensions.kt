package com.example.myapplication

import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.util.regex.Pattern


fun executeAfterDelay(
    executeTimes: Int = 1,
    initialDelay: Long = 1000L,
    repeatDelay: Long = initialDelay,
    action: (count: Int, executeTimes: Int) -> Unit
) {
    //execute time 0 or less then 0 means infinite execution
    var count = 0
    val handler = Handler(Looper.getMainLooper())
    val runnableCode = object : Runnable {
        override fun run() {
            when {
                executeTimes > 0 -> {
                    count++
                }

                else -> {
                    count = executeTimes - 1
                }
            }
            action.invoke(count, executeTimes)
            if (count < executeTimes) {
                //handler.removeCallbacksAndMessages(null)
                val delay = when (repeatDelay) {
                    0L, initialDelay -> {
                        initialDelay
                    }

                    else -> {
                        repeatDelay
                    }
                }
                handler.postDelayed(this, delay)
            }
        }
    }
    handler.postDelayed(runnableCode, initialDelay)
}

fun executeUntil(
    maxTries: Int = 1,
    initialDelay: Long = 1000L,
    repeatDelay: Long = initialDelay,
    action: (maxTries: Int, executeTimes: Int) -> Boolean
) {
    var count = 0
    val handler = Handler(Looper.getMainLooper())
    val runnableCode = object : Runnable {
        override fun run() {
            count++
            val isProcessComplete = action.invoke(maxTries, count)
            if ((count == maxTries) || isProcessComplete) {
                handler.removeCallbacksAndMessages(this)
                return
            } else {
                handler.postDelayed(this, repeatDelay)
            }
        }
    }
    handler.postDelayed(runnableCode, initialDelay)
}

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }


inline fun <reified T> String.deserialize(): T? {
    return try {
        val gson = Gson()
        val type = object : TypeToken<T>() {}.type
        gson.fromJson(this, type)
    } catch (ex: Exception) {
        when (ex) {
            is JsonSyntaxException, is JSONException, is JsonParseException -> {
                ex.printStackTrace()
                return null
            }

            //else -> throw ex
            else -> {
                return null
            }
        }
    }
}

inline fun <reified T> safeCast(any: Any?): T? {
    return if (any is T) {
        any
    } else {
        null
    }
}

inline fun <reified T> safeCast(any: Any?, block: (T) -> Unit) {
    /*if (any is T) {
        block.invoke(any)
    }*/
    safeCast<T>(any)?.let { an ->
        block.invoke(an)
    }
}

inline fun <reified T> safeToCast(any: Any?): T? {
    return any as? T
}

inline fun <reified T> safeToCast(any: Any?, block: (T) -> Unit) {
    /*(any as? T)?.let {
        block.invoke(any)
    }*/
    safeToCast<T>(any)?.let { an ->
        block.invoke(an)
    }
}


val String.cleanTextContent: String
    get() {
        // strips off all non-ASCII characters
        var text = this
        text = text.replace("[^\\x00-\\x7F]".toRegex(), "")

        // erases all the ASCII control characters
        text = text.replace("\\p{Cntrl}&&[^\r\n\t]".toRegex(), "")

        // removes non-printable characters from Unicode
        text = text.replace("\\p{C}".toRegex(), "")
        return text.trim()
    }

fun String.decodeJwtToken(): JSONObject? {
    val token = this
    val payloadIndex = 1
    val parts = token.split("\\.".toRegex()).toTypedArray()
    val payload = parts.getOrNull(payloadIndex)
    val base64String = payload?.decodeFromBase64()
    base64String.ifNotNullNotEmpty { base64 ->
        return try {
            JSONObject(base64)
        } catch (e: Exception) {
            null
        }
    }
    return null
}

fun String.getValue(key: String): String? {
    val token = this
    val payload = token.decodeJwtToken() ?: return null
    return if (payload.has(key)) {
        payload.getString(key) ?: ""
    } else {
        ""
    }
}

fun String.decodeFromBase64(): String {
    return try {
        val encodedBase64String = this
        Base64.decode(encodedBase64String, Base64.URL_SAFE).toString(charset(Charsets.UTF_8.name()))
    } catch (e: Exception) {
        ""
    }
}


fun String.isValidBase64(): Boolean {
    val regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?\$"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)
    return matcher.find()
}


inline fun String?.ifNotNullNotEmpty(block: (String) -> Unit) {
    if (!this.isNullOrEmpty()) {
        block(this)
    }
}

fun String.isTokenExpired(key: String = "exp", allowedTimeDifference: Int = 5): Boolean {
    val token = this
    if (token.isEmpty()) {
        return false
    }
    val currentTime = System.currentTimeMillis()
    val exp = token.getValue(key = key)
    val tokenExpiryTime = when {
        exp == null -> {
            -2L
        }

        exp.isEmpty() -> {
            -1L
        }

        else -> {
            exp.toLong() * 1000
        }
    }

    Log.d(TAG, "getValue: $exp")
    return when (tokenExpiryTime) {
        -1L -> {
            false
        }

        -2L -> {
            true
        }

        else -> {
            val difference = tokenExpiryTime - currentTime
            difference < (allowedTimeDifference * 60000)
        }
    }
}

inline fun <reified T : Fragment>
        instantiateFragment(vararg params: Pair<String, Any>): T =
    T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }

/*
inline fun <reified T : Fragment> newInstance(vararg params: Pair<String, Any>) : T{
            return T::class.java.newInstance().apply {
                arguments = bundleOf(*params)
            }
        }
*/


