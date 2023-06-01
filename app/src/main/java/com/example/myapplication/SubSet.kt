package com.example.myapplication

class SubSet {
    fun <T> subsets(words: List<T>): List<List<T>> {
        val l: MutableList<List<T>> = ArrayList()
        for (i in 0..words.size) {
            subsets(words, i, l, 0, ArrayList())
        }
        return l
    }

    private fun <T> subsets(
        words: List<T>,
        currLength: Int,
        l: MutableList<List<T>>,
        start: Int,
        curr: MutableList<T>
    ) {
        if (curr.size == currLength) {
            l.add(ArrayList(curr))
            return
        }
        for (i in start until words.size) {
            //ignore if element is already visited
            if (i != start && words[i - 1] == words[i]) continue
            curr.add(words[i])
            subsets(words, currLength, l, i + 1, curr)
            curr.removeAt(curr.size - 1)
        }
    }
}