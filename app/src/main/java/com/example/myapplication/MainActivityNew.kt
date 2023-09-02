package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.custom.CustomSnackBar.Companion.showGeneralSnackBar
import com.example.myapplication.custom.SnackBar
import com.example.myapplication.custom.SnackBarType
import com.example.myapplication.custom.showSnackBar
import com.example.myapplication.custom.showUndoSnackBar
import com.google.android.material.button.MaterialButton

class MainActivityNew : AppCompatActivity() {
    private var count = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        /*val list = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        Log.d(TAG, "onCreate 1: $list")
        //val jsonArray = list.toJson()
        val jsonArray = null
        Log.d(TAG, "onCreate 2: $jsonArray")
        val decrypted = jsonArray.ifNotNullNorEmpty { offensiveContent ->
            offensiveContent
        }
        Log.d(TAG, "onCreate 3: $decrypted")
        val words = decrypted.deserialize<List<String>>()
        words.isNotNullNotEmpty { data ->
            Log.d(TAG, "onCreate 4: $data")
        }*/
        val btnShowSnackBar = findViewById<MaterialButton>(R.id.btn_show_snack_bar)
        btnShowSnackBar.text = getString(R.string.show_snack_bar, count.toString())
        btnShowSnackBar.setOnClickListener {
            count++
            btnShowSnackBar.text = getString(R.string.show_snack_bar, count.toString())
            //snackBar(btnShowSnackBar)
            generalSnackBar(btnShowSnackBar)
        }
    }

    private fun snackBar(btnShowSnackBar: MaterialButton) {
        when (count) {
            1 -> {
                showSnackBar(message = "Success $count", snackBarType = SnackBarType.SUCCESS)
            }

            2 -> {
                showSnackBar(message = "Alert $count", snackBarType = SnackBarType.ALERT)
            }

            3 -> {
                showSnackBar(message = "Error $count", snackBarType = SnackBarType.ERROR)
            }

            4 -> {
                showSnackBar(
                    message = "Success Light $count",
                    snackBarType = SnackBarType.SUCCESS_LIGHT
                )
            }

            5 -> {
                showSnackBar(
                    message = "Alert Light $count",
                    snackBarType = SnackBarType.ALERT_LIGHT
                )
            }

            6 -> {
                showSnackBar(
                    message = "Error Light $count",
                    snackBarType = SnackBarType.ERROR_LIGHT
                )
            }

            7 -> {
                showUndoSnackBar(message = "Undo $count")
            }

            8 -> {
                showSnackBar(
                    message = "Action Label $count",
                    actionLbl = "Reset Count $count",
                    cancelFun = { isFinishingOrDestroyed, closedManually ->
                        count = 0
                        btnShowSnackBar.text =
                            getString(R.string.show_snack_bar, count.toString())
                        Log.d(TAG, "FinalSnackBar:$isFinishingOrDestroyed $closedManually ")
                    })
            }

            else -> {
                count = 0
            }
        }
    }

    private fun generalSnackBar(btnShowSnackBar: MaterialButton) {
        when (count) {
            0 -> {
                val snackBar = SnackBar.CUSTOM(
                    setMessage = "ZEESHAN RASOOL",
                    setActionLabel = "Minha",
                    setIcon = 0,
                    setTintColor = 0,
                    setTextColor = 0,
                    setSnackBarLength = 0,
                    setMessageRes = 0,
                    setActionLabelRes = 0,
                    setActionTextColor = 0,
                    setActionType = SnackBar.ACTION_LABEL

                )
                showGeneralSnackBar(
                    message =snackBar.setMessage,
                    snackBar = snackBar,
                    actionLabel = snackBar.setActionLabel,
                    cancelFun = { isFinishingOrDestroyed, closedManually ->
                        count = -1
                        btnShowSnackBar.text =
                            getString(R.string.show_snack_bar, count.toString())
                        Log.d(TAG, "FinalSnackBar:$isFinishingOrDestroyed $closedManually ")
                    })
            }

            1 -> {
                showGeneralSnackBar(message = "Success $count", snackBar = SnackBar.SUCCESS)
            }

            2 -> {
                showGeneralSnackBar(message = "Alert $count", snackBar = SnackBar.ALERT)
            }

            3 -> {
                showGeneralSnackBar(message = "Error $count", snackBar = SnackBar.ERROR)
            }

            4 -> {
                showGeneralSnackBar(
                    message = "Success Light $count",
                    snackBar = SnackBar.SUCCESS_LIGHT
                )
            }

            5 -> {
                showGeneralSnackBar(
                    message = "Alert Light $count",
                    snackBar = SnackBar.ALERT_LIGHT
                )
            }

            6 -> {
                showGeneralSnackBar(
                    message = "Error Light $count",
                    snackBar = SnackBar.ERROR_LIGHT
                )
            }

            7 -> {
                showGeneralSnackBar(message = "Undo $count", snackBar = SnackBar.BUTTON)
            }

            8 -> {
                showGeneralSnackBar(
                    message = "Action Label $count",
                    actionLabel = "Reset Count $count",
                    snackBar = SnackBar.LABEL,
                    cancelFun = { isFinishingOrDestroyed, closedManually ->
                        count = -1
                        btnShowSnackBar.text =
                            getString(R.string.show_snack_bar, count.toString())
                        Log.d(TAG, "FinalSnackBar:$isFinishingOrDestroyed $closedManually ")
                    })
            }

            else -> {
                count = -1
            }
        }
    }
}