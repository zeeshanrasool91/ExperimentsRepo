package com.example.myapplication

import android.util.Log
import java.util.AbstractList
import java.util.Collections
import kotlin.math.ceil

class Partition<T>(list: List<T>, private val chunkSize: Int) : AbstractList<List<T>>() {
    private val list: List<T>

    init {
        this.list = ArrayList(list)
    }

    override fun get(index: Int): List<T> {
        val start = index * chunkSize
        val end = (start + chunkSize).coerceAtMost(list.size)
        Log.d(TAG, "get: $start -- $end")
        if (start > end) {
            throw IndexOutOfBoundsException("Index " + index + " is out of the list range <0," + (size - 1) + ">")
        }
        return ArrayList(list.subList(start, end))
    }

    override val size: Int
        get() = ceil(list.size.toDouble() / chunkSize.toDouble()).toInt()

    companion object {
        fun <T> ofSize(list: List<T>, chunkSize: Int): Partition<T> {
            return Partition(list, chunkSize)
        }
    }
}

fun <T> List<T>.ofSize(chunkSize: Int): Partition<T> {
    return Partition(this, chunkSize)
}