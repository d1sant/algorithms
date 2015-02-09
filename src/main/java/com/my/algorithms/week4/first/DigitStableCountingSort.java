package com.my.algorithms.week4.first;

import java.util.Arrays;

/**
 * Digit sorting (aka Radix sorting) algorithm witch uses stable counting sorting.
 *
 * TODO need to be fixed on set of digits like 100, 1, 10, 11, 1000
 */
public class DigitStableCountingSort {

    public static void main(final String[] args) {

        System.out.println("1st digit of 142 is " + getNthDigit(142, 10, 1));
        System.out.println("2nd digit of 142 is " + getNthDigit(142, 10, 2));
        System.out.println("3rd digit of 142 is " + getNthDigit(142, 10, 3));
        System.out.println("4th digit of 142 is " + getNthDigit(142, 10, 4) + "\n");

        final Integer[] simUnsorted = new Integer[] {100, 1, 10, 11, 1000};
        System.out.println("Unsorted: " + Arrays.toString(simUnsorted));
        System.out.println("Sort by 1st digit: " + Arrays.toString(sort(simUnsorted, 1)));
        System.out.println("Sort by 2nd digit: " + Arrays.toString(sort(simUnsorted, 2)));
        System.out.println("Sort by 3rd digit: " + Arrays.toString(sort(simUnsorted, 3)) + "\n");

        System.out.println("Unsorted: " + Arrays.toString(simUnsorted));
        System.out.println("Sorted: " + Arrays.toString(digitSort(simUnsorted, 3)) + "\n");

        final Integer[] unsorted = new Integer[] {112, 134, 145, 100, 101, 11, 1, 111, 561, 432, 321, 111, 5, 980, 981};
        System.out.println("Unsorted: " + Arrays.toString(unsorted));
        System.out.println("Sort by 1st digit: " + Arrays.toString(sort(unsorted, 1)));
        System.out.println("Sort by 2nd digit: " + Arrays.toString(sort(unsorted, 2)));
        System.out.println("Sort by 3rd digit: " + Arrays.toString(sort(unsorted, 3)) + "\n");

        System.out.println("Unsorted: " + Arrays.toString(unsorted));
        System.out.println("Sorted: " + Arrays.toString(digitSort(unsorted, 3)));
    }

    private static int getNthDigit(int number, int n) {
        return getNthDigit(number, 10, n);
    }

    private static int getNthDigit(int number, int base, int n) {
        return (int) ((number / Math.pow(base, n - 1)) % base);
    }

    public static Integer[] digitSort(final Integer[] unsorted, final int maxRank) {
        Integer[] tempResult = new Integer[]{};
        for (int i = 1; i <= maxRank; i++) {
            tempResult = sort(unsorted, i);
        }
        return tempResult;
    }

    private static Integer[] sort(final Integer[] unsorted, final int rank) {
        final int[] counters = new int[10];
        for (int index = 0; index < 10; index++) {
            counters[index] = 0;
        }
        for (final Integer value : unsorted) {
            final int nthDigit = getNthDigit(value, rank);
            counters[nthDigit] = counters[nthDigit] + 1;
        }
        for (int index = 1; index < 10; index++) {
            counters[index] = counters[index] + counters[index - 1];
        }
        final Integer[] result = new Integer[unsorted.length];
        for (int j = unsorted.length - 1; j >= 0; j--) {
            final Integer value = unsorted[j];
            final Integer nthDigit = getNthDigit(value, rank);
            result[counters[nthDigit] - 1] = value;
            counters[nthDigit] = counters[nthDigit] - 1; // TODO refactor me
        }
        return result;
    }
}
