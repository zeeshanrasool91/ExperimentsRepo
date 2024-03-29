package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
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


/*
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
*/

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

fun <T> MutableList<T>.createChunks(chunkSize: Int = 10): MutableList<MutableList<T>> {
    val items = this
    val chunks = mutableListOf<MutableList<T>>()
    val totalSize = items.size
    var totalChunkedSize = 0
    var count = 0
    var tempList = mutableListOf<T>()
    var remainingItems = 0
    var chunksCount = 0
    if (chunkSize == 0 || items.isEmpty()) {
        tempList = mutableListOf()
        if(items.isNotEmpty()) {
            tempList.addAll(items)
            chunks.add(tempList)
        }
        return chunks
    }
    while (totalChunkedSize != totalSize) {
        count++
        totalChunkedSize = count
        tempList.add(items[count - 1])
        if (totalChunkedSize % chunkSize == 0) {
            chunks.add(tempList)
            tempList = mutableListOf()
            chunksCount += chunkSize
        }
        remainingItems = totalChunkedSize - chunksCount
    }
    val startIndex = items.size - remainingItems
    tempList = mutableListOf()
    for (i in startIndex until items.size) {
        tempList.add(items[i])
    }
    if (tempList.isNotEmpty()) {
        chunks.add(tempList)
    }
    return chunks
}


fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
    isFocusableInTouchMode = false
    isClickable = true
    isFocusable = false

    val myCalendar = Calendar.getInstance()
    val datePickerOnDataSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            setText(sdf.format(myCalendar.time))
        }

    setOnClickListener {
        DatePickerDialog(
            context, datePickerOnDataSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).run {
            maxDate?.time?.also { datePicker.maxDate = it }
            show()
        }
    }
}

inline fun String?.ifNotNullNorEmpty(block: (String) -> String): String {
    return if (!this.isNullOrEmpty()) {
        block(this)
    } else {
        orEmpty()
    }
}

inline fun <reified T> String?.deserialize(): T? {
    return try {
            val gson = getGson()
            val type = object : TypeToken<T>() {}.type
            gson.fromJson(this, type)
    } catch (e: Exception) {
        null
    }
}

inline fun <T> List<T>?.isNotNullNotEmpty(block: (List<T>) -> Unit) {
    if (!this.isNullOrEmpty()) {
        block(this)
    }
}
fun Any.toJson(): String? {
    return getGson().toJson(this)
}

fun getGson(): Gson {
    return GsonBuilder().create()
}

fun Context.getColorCompat(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun String?.isNotNullNorEmpty(): Boolean = this.isNullOrEmpty().not()

fun View.invisible(invisible: Boolean = true) {
    this.isInvisible = invisible
}

fun View.gone(gone: Boolean = true) {
    this.isGone = gone
}

fun View.visible(visible: Boolean = true) {
    this.isVisible = visible
}

fun View.disable() {
    this.isEnabled = false
}

fun View.enable() {
    this.isEnabled = true
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)))
}
/*Usage:
In Activity:
editText.transformIntoDatePicker(this, "MM/dd/yyyy")
editText.transformIntoDatePicker(this, "MM/dd/yyyy", Date())*/


