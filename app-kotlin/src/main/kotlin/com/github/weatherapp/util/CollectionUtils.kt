package com.github.weatherapp.util

object CollectionUtils {

    fun <T> indexOf(array: Array<T>, item: T): Int {
        var i = 0

        for (it in array) {
            if (it == item) return i
            ++i
        }

        return -1
    }
}
