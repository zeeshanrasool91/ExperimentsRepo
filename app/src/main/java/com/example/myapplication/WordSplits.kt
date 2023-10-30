package com.example.myapplication

import java.util.regex.Pattern

object WordSplits {
    private fun first2rest(wordList: List<String>): Map<String, MutableList<String>> {
        val first2RestWords: MutableMap<String, MutableList<String>> = HashMap()
        for (word in wordList) {
            // TODO Make it use Pattern. Sample demo. Get the first word of
            // every string.
            val splits = word.split("\\W".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val firstWord = splits[0]
            var restWords = first2RestWords[firstWord]
            if (restWords == null) {
                restWords = ArrayList()
            }
            restWords.add(word)
            // store the complete pattern nevertheless
            first2RestWords[firstWord] = restWords
        }
        return first2RestWords
    }

    private fun longestSubstring(
        line: String,
        first: List<String>, second: List<String>
    ): Map<String, List<Int>> {
        val occurrences: Map<String, List<Int>> = LinkedHashMap()
        val first2RestWords = first2rest(first)
        val second2RestWords = first2rest(second)
        val wordMatcher = Pattern.compile("\\w+").matcher(line)
        var start = 0
        while (start < line.length && wordMatcher.find(start)) {
            val word = wordMatcher.group()
            var maxWordFirst = ""
            var maxWordSecond = ""
            if (first2RestWords.containsKey(word)) {
                maxWordFirst = longestMatch(
                    line.substring(wordMatcher.start()),
                    first2RestWords[word]!!
                )
            }
            if (second2RestWords.containsKey(word)) {
                maxWordSecond = longestMatch(
                    line.substring(wordMatcher.start()),
                    second2RestWords[word]!!
                )
            }
            if (maxWordFirst.isNotEmpty() || maxWordSecond.isNotEmpty()) {
                if (maxWordFirst == maxWordSecond) {
                    println("Belongs to both the lists : $maxWordFirst")
                } else {
                    if (maxWordFirst.length > maxWordSecond.length) {
                        println("Belongs to first list:  $maxWordFirst")
                    } else if (maxWordSecond.length > maxWordFirst.length) {
                        println("Belongs to second list: $maxWordSecond")
                    }
                }
            } else {
                println("$word does not belong to any list")
            }
            // Take some action
            start = wordMatcher.start() + maxWordFirst.length.coerceAtLeast(maxWordSecond.length) + 1
            start = wordMatcher.end().coerceAtLeast(start)
        }
        return occurrences
    }

    private fun longestMatch(line: String, wordList: List<String>): String {
        var maxWord = ""
        // poor way to compare
        for (word in wordList) {
            if (line.startsWith(word) && word.length > maxWord.length) {
                maxWord = word
            }
        }
        return maxWord
    }

    @JvmStatic
    fun main(args: Array<String>) {
        longestSubstring(
            "artificial intelligence is cool. bat.",
            mutableListOf("dog", "cow", "dog", "artificial intelligence", "bat"),
            mutableListOf("artificial", "hound", "cool", "bat", "dog hound")
        )
    }
}