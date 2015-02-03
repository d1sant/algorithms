package com.my.algorithms.week4.third;

import java.util.Arrays;

/**
 * Merge sort algorithm implementation.
 */
public class MergeSort {

    public static void main(final String[] args) {
        final int[] unsorted = {2, 3, 2, 1, 2};
        sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsortedValues) {
        // TODO Implement me
    }

    private static int[] mergeSort(final int[] unsorted, final int left, final int right) {
        if (left >= right) {
            return new int[]{};
        }
        final int middle = (left + right) / 2;
        return merge(mergeSort(unsorted, left, middle), mergeSort(unsorted, middle + 1, right));
    }

    private static int[] merge(final int[] first, final int[] second) {
        return new int[]{}; // TODO Implement me
    }
}
