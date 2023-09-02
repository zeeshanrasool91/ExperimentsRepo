package com.example.myapplication.custom

import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar

enum class SnackBarType(
    var setStatus: Int,
    var setCardBackgroundColor: Int,
    var setMessage: String? = null,
    var setActionLabel: String? = null,
    var setIcon: Int = R.drawable.ic_success,
    var setTintColor: Int = R.color.white,
    var setTextColor: Int = R.color.white,
    var setSnackBarLength: Int = Snackbar.LENGTH_SHORT,
    var setMessageRes: Int = R.string.lbl_error_state,
    var setActionLabelRes: Int = R.string.lbl_error_state,
    var setActionTextColor: Int = R.color.white,
    var setActionType: Int = SnackBarType.ACTION_CLOSE
) : SnackBarTypeMetaData {

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

        override fun getMessage(
        ): Int {
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

        override fun getTintColor(): Int {
            return R.color.snack_bar_success_light_text_color
        }

        override fun getTextColor(): Int {
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

        override fun getTintColor(): Int {
            return R.color.snack_bar_alert_light_text_color
        }

        override fun getTextColor(): Int {
            return R.color.snack_bar_alert_light_text_color
        }

    },
    ERROR_LIGHT(5, R.color.snack_bar_error_light) {

        override fun getMessage(
        ): Int {
            return R.string.lbl_error_state
        }

        override fun getIcon(): Int {
            return R.drawable.ic_error
        }

        override fun getTintColor(): Int {
            return R.color.snack_bar_error_light_text_color
        }

        override fun getTextColor(): Int {
            return R.color.snack_bar_error_light_text_color
        }
    },
    BUTTON(6, R.color.snack_bar_error_light) {

        override fun getMessage(
        ): Int {
            return R.string.lbl_undo
        }

        override fun getIcon(): Int {
            return R.drawable.ic_delete_outlined
        }

        override fun getTintColor(): Int {
            return R.color.error
        }

        override fun getTextColor(): Int {
            return R.color.error
        }

        override fun getActionType(): Int {
            return SnackBarType.ACTION_BUTTON
        }

        override fun getActionLabel(
        ): Int {
            return R.string.lbl_undo
        }
    },
    LABEL(6, R.color.snack_bar_default) {

        override fun getMessage(): Int {
            return R.string.lbl_restart
        }

        override fun getActionType(): Int {
            return SnackBarType.ACTION_LABEL
        }

        override fun getActionLabel(): Int {
            return R.string.lbl_restart
        }
    },
    CUSTOM(7, R.color.snack_bar_default);

    companion object {
        const val ACTION_CLOSE = 0
        const val ACTION_BUTTON = 1
        const val ACTION_LABEL = 2
        const val NO_ACTION = -1
    }
}

interface SnackBarTypeMetaData {
    fun getIcon(): Int = R.drawable.ic_success
    fun getTintColor(): Int = R.color.white
    fun getTextColor(): Int = R.color.white
    fun getSnackBarLength(): Int = Snackbar.LENGTH_SHORT

    fun getMessage(): Int = R.string.lbl_error_state

    fun getActionLabel(): Int = R.string.lbl_error_state
    fun getActionTextColor(): Int = R.color.white

    fun getActionType(): Int = SnackBarType.ACTION_CLOSE
}