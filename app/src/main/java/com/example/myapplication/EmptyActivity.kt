package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import com.example.myapplication.databinding.ActivityEmptyBinding
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.contracts.contract

class EmptyActivity : AppCompatActivity() {
    val TWELVE_HOUR_FORMAT = "h:mm a"
    val TWENTY_FOUR_HOUR_FORMAT = "HH:mm"

    private lateinit var binding: ActivityEmptyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmptyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val models = mutableListOf<TestJava>()
        val zero = TestJava()
        zero.text = null

        models.add(TestJava())
        val one = TestJava()
        one.text = ""
        models.add(one)
        val two = TestJava()
        two.text = "2"
        models.add(two)
        val fromNodes = getTextFromNodes(models)
        Log.d(TAG, "onCreate: $fromNodes")


        val time12 ="7:37 PM"
        val time24 ="19:37"
        Log.d(TAG, "onCreate 12: ${convert12HourFormatToMillis(time12)}")
        Log.d(TAG, "onCreate 24: ${convert24HourFormatToMillis(time24)}")
    }

    fun convert12HourFormatToMillis(dateString: String): Long {
        val sdf = SimpleDateFormat(TWELVE_HOUR_FORMAT, Locale.US)
        try {
            val date = sdf.parse(dateString)
            return date?.time ?: 0
        } catch (e: ParseException) {
            //e.printStackTrace()
        }
        return  0
    }
    fun convert24HourFormatToMillis(dateString: String): Long {
        val sdf = SimpleDateFormat(TWENTY_FOUR_HOUR_FORMAT, Locale.US)
        try {
            val date = sdf.parse(dateString)
            return date?.time ?: 0
        } catch (e: ParseException) {
            //e.printStackTrace()
        }
        return  0
    }


    fun getTextFromNodes(nodes: List<TestJava>?, first: Boolean = true): String? {
        var text: String? = null
        nodes.isNotNullNotEmpty { node ->
            val latest = if (first) node.first() else node.last()
            if (latest.text.isNotNullNotEmpty() && latest.text.toString().isNotEmpty() ) {
                text = latest.text.toString()
            }
        }
        return text
    }

    private inline fun <T> List<T>?.isNotNullNotEmpty(block: (List<T>) -> Unit) {
        if (!this.isNullOrEmpty()) {
            block(this)
        }
    }

    private fun CharSequence?.isNotNullNotEmpty(): Boolean {
        return this.isNullOrEmpty().not()
    }

}