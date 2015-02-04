package com.my.algorithms.week4.fourth;

import java.util.Arrays;
import java.util.Random;

/**
 * Eliminated quick sort algorithm with random pivot element.
 * It has constant depth of recurrent invocations equaled to log(n)
 */
public class EliminatedQuickSort extends AbstractQuickSort {

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
    protected void quickSort(int[] unsorted, int left, int right) {
        while (left < right) {
            final int pivotIndex = partition(unsorted, left, right);
            if ((pivotIndex - left) >= (right - pivotIndex)) {
                quickSort(unsorted, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                quickSort(unsorted, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
        }
    }

    @Override
    protected void definePivot(final int[] unsorted, final int left, final int right) {
        final int pivotIndex = random.nextInt((right - left) + 1) + left;
        swap(unsorted, pivotIndex, 0);
    }
}
