package com.my.algorithms.week1;

import java.util.Arrays;

/**
 * Counting sort implementation.
 */
public class CountingSort {

    public static void main(String[] args) {
        final int[] unsorted = {5, 1, 3, 1, 5, 8, 9};
        sort(unsorted, 10);
        System.out.println(Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsortedValues, final int maxValue) {
        final int[] counters = new int[maxValue];
        for (int i = 0; i < maxValue; i++) {
            counters[i] = 0;
        }
        for (final int i : unsortedValues) {
            counters[i - 1] = counters[i - 1] + 1;
        }
        int index = 0;
        for (int counterIndex = 0; counterIndex < maxValue; counterIndex++) {
            for (int valueIndex = 0; valueIndex < counters[counterIndex]; valueIndex++) {  // TODO refactor
                unsortedValues[index] = counterIndex + 1;
                index++;
            }
        }
    }
}
