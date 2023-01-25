package com.example.myapplication

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import org.json.JSONException

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
    safeCast<T>(any)?.let{ an  ->
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
    safeToCast<T>(any)?.let{ an  ->
        block.invoke(an)
    }
}


