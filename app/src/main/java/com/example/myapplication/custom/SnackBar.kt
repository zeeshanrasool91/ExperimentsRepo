package com.example.myapplication.custom

import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar
import java.sql.ClientInfoStatus

sealed class SnackBar(open val status: Int, open val cardBackgroundColor: Int) :
    SnackBarMetaData {

    object SUCCESS : SnackBar(status = 0, cardBackgroundColor = R.color.snack_bar_default) {
        override fun getMessage(): Int {
            return R.string.lbl_success_state
        }
    }

    object ALERT : SnackBar(status = 1, cardBackgroundColor = R.color.snack_bar_default) {
        override fun getMessage(): Int {
            return R.string.lbl_alert_state
        }

        override fun getIcon(): Int {
            return R.drawable.ic_alert
        }

    }

    object ERROR : SnackBar(status = 2, cardBackgroundColor = R.color.snack_bar_default) {

        override fun getMessage(
        ): Int {
            return R.string.lbl_error_state
        }

        override fun getIcon(): Int {
            return R.drawable.ic_error
        }
    }

    object SUCCESS_LIGHT : SnackBar(
        status = 3,
        cardBackgroundColor = R.color.snack_bar_success_light
    ) {
        override fun getMessage(): Int {
            return R.string.lbl_success_state
        }

        override fun getTintColor(): Int {
            return R.color.snack_bar_success_light_text_color
        }

        override fun getTextColor(): Int {
            return R.color.snack_bar_success_light_text_color
        }
    }

    object ALERT_LIGHT : SnackBar(status = 4, cardBackgroundColor = R.color.snack_bar_alert_light) {

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

    }

    object ERROR_LIGHT : SnackBar(status = 5, cardBackgroundColor = R.color.snack_bar_error_light) {

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
    }

    object BUTTON : SnackBar(status = 6, cardBackgroundColor = R.color.snack_bar_error_light) {

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
    }

    object LABEL : SnackBar(6, R.color.snack_bar_default) {

        override fun getMessage(): Int {
            return R.string.lbl_restart
        }

        override fun getActionType(): Int {
            return SnackBarType.ACTION_LABEL
        }

        override fun getActionLabel(): Int {
            return R.string.lbl_restart
        }
    }

    data class CUSTOM(
       /* override val status: Int = 7,
        override val cardBackgroundColor: Int = R.color.snack_bar_default,*/
        var setMessage: String? = null,
        var setActionLabel: String? = null,
        var setIcon: Int = R.drawable.ic_success,
        var setTintColor: Int = R.color.white,
        var setTextColor: Int = R.color.white,
        var setSnackBarLength: Int = Snackbar.LENGTH_SHORT,
        var setMessageRes: Int = R.string.lbl_error_state,
        var setActionLabelRes: Int = R.string.lbl_error_state,
        var setActionTextColor: Int = R.color.white,
        var setActionType: Int = NO_ACTION
    ) : SnackBar(status = 7, cardBackgroundColor = R.color.snack_bar_default) {

    }
   /* ) : SnackBar(status = status, cardBackgroundColor = cardBackgroundColor) {
    }*/

    companion object {
        const val ACTION_CLOSE = 0
        const val ACTION_BUTTON = 1
        const val ACTION_LABEL = 2
        const val NO_ACTION = -1
    }
}


interface SnackBarMetaData {
    fun getIcon(): Int = R.drawable.ic_success
    fun getTintColor(): Int = R.color.white
    fun getTextColor(): Int = R.color.white
    fun getSnackBarLength(): Int = Snackbar.LENGTH_SHORT

    fun getMessage(): Int = R.string.lbl_error_state

    fun getActionLabel(): Int = R.string.lbl_error_state
    fun getActionTextColor(): Int = R.color.white

    fun getActionType(): Int = SnackBarType.ACTION_CLOSE
}

