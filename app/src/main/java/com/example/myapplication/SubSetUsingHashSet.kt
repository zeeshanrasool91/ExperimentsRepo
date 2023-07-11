package com.example.myapplication

import android.util.Log

class SubSetUsingHashSet {
    fun <T> subsets(words: List<T>, maxIteration: Int = -1): List<HashSet<T>> {
        val finalList: MutableList<HashSet<T>> = ArrayList()
        for (i in 0..words.size) {
            subsets(words, i, finalList, 0, ArrayList(), maxIteration)
        }


        return if (maxIteration <= 0) {
            finalList.distinct()
        } else {
            finalList.filter {
                it.size == maxIteration
            }.distinct()
        }
    }

    private fun <T> subsets(
        words: List<T>,
        currLength: Int,
        l: MutableList<HashSet<T>>,
        start: Int,
        curr: MutableList<T>,
        maxIteration: Int
    ) {
        Log.d(TAG, "subsets: $currLength")
        if (curr.size == currLength) {
            val list = HashSet(curr)
            if (list.isNotEmpty()) {
                l.add(list)
            }
            return
        }
        for (i in start until words.size) {
            //ignore if element is already visited
            if (i != start && words[i - 1] == words[i])
                continue
            curr.add(words[i])
            subsets(words, currLength, l, i + 1, curr, maxIteration)
            curr.removeAt(curr.size - 1)
        }
    }
}