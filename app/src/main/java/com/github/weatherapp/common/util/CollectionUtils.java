package com.github.weatherapp.common.util;

public class CollectionUtils {

    public static <T> int indexOf(T[] array, T item) {
        int i = 0;

        for (T it : array) {
            if (it.equals(item)) return i;
            ++i;
        }

        return -1;
    }
}
