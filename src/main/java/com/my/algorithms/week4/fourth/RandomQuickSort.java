package com.my.algorithms.week4.fourth;

import java.util.Arrays;
import java.util.Random;

import static com.my.algorithms.tools.Arrays.swap;

/**
 * Quick sort algorithm with random pivot element selection.
 */
public class RandomQuickSort extends AbstractQuickSort {

    private final Random random = new Random();

    public static void main(final String[] args) {

        final int[] unsorted = {3, 6, 2, 1, 8, 7, 3, 5};
        System.out.println("Initial values: " + Arrays.toString(unsorted));
        sort(unsorted);
        System.out.println("Sorted values: " + Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsortedValues) {
        new QuickSort().quickSort(unsortedValues, 0, unsortedValues.length - 1);
    }

    @Override
    protected void definePivot(final int[] unsorted, final int left, final int right) {
        final int pivotIndex = random.nextInt((right - left) + 1) + left;
        swap(unsorted, pivotIndex, 0);
    }
}
