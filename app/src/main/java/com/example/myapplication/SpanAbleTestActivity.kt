package com.example.myapplication

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
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
        val domains = listOf(
            "www.google.com.pk/abcd",
            "drive.google.com",
            "geeksforgeeks.org",
            "write.geeksforgeeks.org",
            "-geeksforgeeks.org",
            "geeksforgeeks.o",
            ".org",
            "https://www.geeksforgeeks.org/how-to-validate-a-domain-name-using-regular-expression/",
            "https://www.geeksforgeeks.org/"
        )
        domains.forEach {
            Log.d(TAG, "isValidDomain: ${it}\n${isValidDomain(it)}")
        }
        val words = listOf("spooge")
        val sentence = "You kid searched for spooge word".appendInvertedCommas(words = words)
        binding.textView.text =
            sentence.highlightUsingRegex(words = words, color = getColorCompat(R.color.error))
    }
}

fun isValidDomain(domain: String): Boolean {
    //val pattern = "^(https?://)?(www\\.)?([a-zA-Z0-9-]+\\.[a-zA-Z]{2,})(?:/|\$)"
    val pattern = ("^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}")
    //val pattern = "[(htps)?:/w.a-zA-Z0-9@%_+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_+.~#?&/=]*)"
    return domain.matches(pattern.toRegex())
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