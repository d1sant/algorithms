package com.my.algorithms.week4.first;

import java.util.Arrays;
import static com.my.algorithms.tools.Arrays.swap;

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
                swap(unsorted, secondIndex, secondIndex - 1);
                secondIndex--;
            }
        }
    }
}
