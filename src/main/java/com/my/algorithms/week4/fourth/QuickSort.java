package com.my.algorithms.week4.fourth;

import java.util.Arrays;

/**
 * Quick sort algorithm implementation.
 */
public class QuickSort {

    public static void main(final String[] args) {
        final int[] unsorted = {2, 3, 2, 1, 2};
        sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsortedValues) {
        // TODO Implement me
    }

    private static void quickSort(final int[] unsorted, final int left, final int right) {
        if (left >= right) {
            return;
        }
        final int pivotIndex = partition(unsorted, left, right);
        quickSort(unsorted, left, pivotIndex - 1);
        quickSort(unsorted, pivotIndex + 1, right);
    }

    private static int partition(final int[] unsorted, final int left, final int right) {
        int pivot = unsorted[left];
        int i, j; i = j = left + 1;
        while (i <= right) {
            if (unsorted[i] <= pivot) {
                j++;
                swap(unsorted, i, j);
            }
        }
        swap(unsorted, left, j);
        return j;
    }

    private static void swap(final int[] array, final int fromIndex, final int toIndex) {
        final Integer to = array[toIndex];
        array[toIndex] = array[fromIndex];
        array[fromIndex] = to;
    }
}
