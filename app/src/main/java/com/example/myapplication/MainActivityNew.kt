package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.custom.SnackBarType
import com.example.myapplication.custom.showSnackBar
import com.example.myapplication.custom.showUndoSnackBar
import com.google.android.material.button.MaterialButton

class MainActivityNew : AppCompatActivity() {
    var count = 0
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
                        })
                }

                else -> {
                    count = 0
                }
            }
        }
    }
}