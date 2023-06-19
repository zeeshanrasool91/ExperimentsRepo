package com.example.myapplication


class GenerateAllSubsetsOfSizeK {
    private fun getSubsets(
        superSet: List<String>,
        k: Int,
        idx: Int,
        current: MutableSet<String>,
        solution: MutableList<Set<String>>
    ) {
        //successful stop clause
        if (current.size == k) {
            solution.add(HashSet(current))
            return
        }
        //unseccessful stop clause
        if (idx == superSet.size) return
        val x = superSet[idx]
        current.add(x)
        //"guess" x is in the subset
        getSubsets(superSet, k, idx + 1, current, solution)
        current.remove(x)
        //"guess" x is not in the subset
        getSubsets(superSet, k, idx + 1, current, solution)
    }

    fun getSubsets(superSet: List<String>, k: Int): List<Set<String>> {
        val res: MutableList<Set<String>> = ArrayList()
        getSubsets(superSet, k, 0, HashSet(), res)
        return res
    }
}