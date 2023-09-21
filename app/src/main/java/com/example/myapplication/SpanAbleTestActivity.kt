package com.example.myapplication

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySpanableTestBinding
import java.util.regex.Pattern

class SpanAbleTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpanableTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpanableTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val words = listOf("spooge")
        val sentence = "You kid searched for spooge word".appendInvertedCommas(words = words)
        binding.textView.text =
            sentence.highlightUsingRegex(words = words, color = getColorCompat(R.color.error))
    }
}

fun String.highlightUsingRegex(
    words: List<String>,
    color: Int
): Spannable {
    val spannedString = SpannableStringBuilder(this)
    words.forEach { word ->
        spannedString.highlightUsingRegex(word, color)
    }
    return spannedString
}


fun Spannable.highlightUsingRegex(
    word: String,
    color: Int
) {
    val quote = Pattern.quote(word)
    val pattern = Pattern.compile(quote, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    while (matcher.find()) {
        val highlightSpan = ForegroundColorSpan(color)
        val startPos = matcher.start()
        val endPos = matcher.end()
        this.setSpan(
            highlightSpan,
            startPos,
            endPos,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

fun String.appendInvertedCommas(words: List<String>): String {
    var sentence = this
    words.forEach { word ->
        val quote = word.toRegex()
        sentence = this.replace(quote, "\"$word\"")
    }
    return sentence
}