package com.example.myapplication

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

object TimeUtils {

    fun formatDate(
        timestampInMillis: Long,
        format: String = "EEEE, MMM d",
        locale: Locale = Locale.getDefault()
    ): String {
        val millionSeconds = timestampInMillis - getCurrentTimeInMillis()
        val days = TimeUnit.MILLISECONDS.toDays(millionSeconds).toInt()
        val dateFormat = SimpleDateFormat(format, locale)
        val oi = getOrdinalIndicator(timestampInMillis)
        return when (days) {
            -1 -> {
                "Yesterday"
            }

            0 -> {
                "Today"
            }

            else -> {
                dateFormat.apply {
                    applyPattern("EEEE - MMM d'$oi', yyyy")
                }.format(timestampInMillis)
            }
        }
    }

    private fun getOrdinalIndicator(timestampInMillis: Long): String {
        val day = newCalendar(timestampInMillis).get(Calendar.DAY_OF_MONTH)

        if (day == 11 || day == 12 || day == 13) {
            return "th"
        }

        return when (day % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

    private fun newCalendar(timestampInMillis: Long): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = timestampInMillis
        }
    }

    private fun getCurrentTimeInMillis(): Long {
        return Calendar.getInstance().timeInMillis
    }

    fun getDates(day: Int): Long {
        Log.d(TAG, "getDates: $day")
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -day)
        return calendar.timeInMillis
    }
}