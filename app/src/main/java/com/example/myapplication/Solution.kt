package com.example.myapplication

object Solution {
    fun ksubsets(
        arr: List<String>, left: Int, idx: Int,
        curArr: ArrayList<String>, result: ArrayList<ArrayList<String>>
    ) {
        if (left <= 0) {
            val tmp = ArrayList(curArr)
            result.add(tmp)
            return
        }
        for (i in idx until arr.size) {
            curArr.add(arr[i])
            ksubsets(arr, left - 1, i + 1, curArr, result)
            curArr.removeAt(curArr.size - 1)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val words = "Hello Zeeshan How are you".split(" ")
        val left = 2 // specifies the subset size
        val idx = 0
        val curArr: ArrayList<String> = ArrayList()
        val result: ArrayList<ArrayList<String>> = ArrayList() // contains all calculated subsets
        ksubsets(words, left, idx, curArr, result)
        println(result.size)
        println(result)
    }
}