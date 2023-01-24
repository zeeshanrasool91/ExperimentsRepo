package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d(TAG, "onCreate1: "+ safeCast<EmptyActivity>(this))
        Log.d(TAG, "onCreate2: "+ safeToCast<EmptyActivity>(this))

        safeCast<EmptyActivity>(this){
            it.actionBar
        }


        /*executeUntil(maxTries = 5, initialDelay = 1000L) { maxTries, executeTimes ->
            val condition = executeTimes == 20
            Log.d(TAG, "onCreate: $maxTries$executeTimes${condition}")
            condition
        }*/
        //val list= listOf("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
        /*val list = listOf(
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        ).toString()*/

        /*val json="[\n" +
                "  {\n" +
                "    \"LotDescription\": \"David Weekley homes Traditional Collection in Baxter Village offers floor plans featuring innovative design and unsurpassed quality. This charming community combines work, play and living, all within the Village. In Baxter Village, you&rsquo;ll enjoy:&nbsp; Parks, playgrounds\"\n" +
                "  }\n" +
                "]"*/
        //val jsonString = Gson().toJson(list)
        //val gson=GsonBuilder().setLenient().create()
        //val jsonString = gson.toJson(json)
        //val deserialize = jsonString.deserialize<List<String>>()
        //Log.d(TAG, "onCreate: $deserialize")


        //current
        /*val baseDateTimeString="2023-01-19 10:00:00"
        val baseDateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH)
        baseDateFormatter.timeZone = TimeZone.getTimeZone("Asia/Dubai") // better than using IST
        Log.d(TAG, "onCreate: $baseDateTimeString")
        val date = baseDateFormatter.parse(baseDateTimeString)
        Log.d(TAG, "onCreate: $date")
        BeforeJava8DateTimeZoneConversion.convertToRequiredTimeZone("yyyy-MM-dd HH:mm:ss",date,"Asia/Karachi")*/
    }
}