package com.my.algorithms.week4.first;

import java.util.Arrays;

/**
 * Stable counting sort algorithm implementation that doesn't change the order of equaled elements.
 */
public class StableCountingSort {

    public static void main(String[] args) {

        final Integer[] unsorted = {new Integer(2), new Integer(3), new Integer(2), new Integer(1), new Integer(2)};
        System.out.println("Unsorted: " + Arrays.toString(unsorted));
        System.out.println("Unsorted raw: " + arrayToRawString(unsorted) + "\n");

        final Integer[] sorted = sort(unsorted, 3);
        System.out.println("Sorted: " + Arrays.toString(sorted));
        System.out.println("Sorted raw: " + arrayToRawString(sorted) + "\n");

        final Integer[] bigUnsorted = new Integer[] {1, 3, 2, 6, 9, 4, 1, 11, 5, 7, 5};
        System.out.println("Unsorted: " + Arrays.toString(bigUnsorted));
        final Integer[] bigSorted = sort(bigUnsorted, 11);
        System.out.println("Sorted: " + Arrays.toString(bigSorted));
    }

    public static Integer[] sort(final Integer[] unsorted, final int maxValue) {
        final int[] counters = new int[maxValue];
        for (int i = 0; i < maxValue; i++) {
            counters[i] = 0;
        }
        for (final int i : unsorted) {
            counters[i - 1] = counters[i - 1] + 1;
        }
        for (int i = 1; i < maxValue; i++) {
            counters[i] = counters[i] + counters[i - 1];
        }
        final Integer[] result = new Integer[unsorted.length];
        for (int j = unsorted.length - 1; j >= 0; j--) {
            final Integer value = unsorted[j];
            result[counters[value - 1] - 1] = value;
            counters[value - 1] = counters[value - 1] - 1; // TODO refactor me
        }
        return result;
    }

    private static String arrayToRawString(final Integer[] values) {
        final StringBuilder result = new StringBuilder("[");
        for (final Integer value : values) {
            result.append(value).append(":").append(System.identityHashCode(value)).append(", ");
        }
        result.delete(result.length() - 2, result.length());
        return result.append("]").toString();
    }
}
