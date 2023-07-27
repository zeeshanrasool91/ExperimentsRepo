package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivityNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        val list = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
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
        }
    }
}