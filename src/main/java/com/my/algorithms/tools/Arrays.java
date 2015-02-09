package com.my.algorithms.tools;

public class Arrays {

    public static String toString(final int[] a) {
        return java.util.Arrays.toString(a);
    }

    public static void swap(final int[] array, final int fromIndex, final int toIndex) {
        final Integer to = array[toIndex];
        array[toIndex] = array[fromIndex];
        array[fromIndex] = to;
    }
}
