package com.my.algorithms.week4.first;

import com.my.algorithms.tools.Arrays;

/**
 * Inserting sort algorithm implementation.
 */
public class InsertingSort {

    public static void main(String[] args) {
        final int[] unsorted = {2, 3, 2, 1, 2};
        sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsorted) {
        for (int firstIndex = 1; firstIndex < unsorted.length; firstIndex++) {
            int secondIndex = firstIndex;
            while (secondIndex > 0 && unsorted[secondIndex] < unsorted[secondIndex - 1]) {
                Arrays.swap(unsorted, secondIndex, secondIndex - 1);
                secondIndex--;
            }
        }
    }
}
