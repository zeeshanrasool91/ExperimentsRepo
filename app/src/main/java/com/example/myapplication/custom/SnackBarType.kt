package com.example.myapplication.custom

import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar

enum class SnackBarType(val status: Int, val cardBackgroundColor: Int) : SnackBarTypeMetaData {
    SUCCESS(0, R.color.snack_bar_default) {
        override fun getMessage(): Int {
            return R.string.lbl_success_state
        }
    },
    ALERT(1, R.color.snack_bar_default) {
        override fun getMessage(): Int {
            return R.string.lbl_alert_state
        }

        override fun getIcon(): Int {
            return R.drawable.ic_alert
        }
    },
    ERROR(2, R.color.snack_bar_default) {

        override fun getMessage(): Int {
            return R.string.lbl_error_state
        }

        override fun getIcon(): Int {
            return R.drawable.ic_error
        }
    },
    SUCCESS_LIGHT(3, R.color.snack_bar_success_light) {
        override fun getMessage(): Int {
            return R.string.lbl_success_state
        }

        override fun tintColor(): Int {
            return R.color.snack_bar_success_light_text_color
        }

        override fun textColor(): Int {
            return R.color.snack_bar_success_light_text_color
        }
    },
    ALERT_LIGHT(4, R.color.snack_bar_alert_light) {

        override fun getMessage(): Int {
            return R.string.lbl_alert_state
        }

        override fun getIcon(): Int {
            return R.drawable.ic_alert
        }

        override fun tintColor(): Int {
            return R.color.snack_bar_alert_light_text_color
        }

        override fun textColor(): Int {
            return R.color.snack_bar_alert_light_text_color
        }

    },
    ERROR_LIGHT(5, R.color.snack_bar_error_light) {

        override fun getMessage(): Int {
            return R.string.lbl_error_state
        }

        override fun getIcon(): Int {
            return R.drawable.ic_error
        }

        override fun tintColor(): Int {
            return R.color.snack_bar_error_light_text_color
        }

        override fun textColor(): Int {
            return R.color.snack_bar_error_light_text_color
        }
    }
}

interface SnackBarTypeMetaData {
    fun getMessage(): Int
    fun getIcon(): Int = R.drawable.ic_success
    fun tintColor(): Int = R.color.white
    fun textColor(): Int = R.color.white
    fun snackBarLength(): Int = Snackbar.LENGTH_SHORT
}