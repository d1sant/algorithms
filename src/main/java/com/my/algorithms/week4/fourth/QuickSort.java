package com.my.algorithms.week4.fourth;

import java.util.Arrays;

/**
 * Quick sort algorithm implementation.
 */
public class QuickSort {

    public static void main(final String[] args) {

        final int[] unsortedForPivot = {6, 4, 3, 9, 2, 7, 11, 1};
        System.out.println("Initial values: " + Arrays.toString(unsortedForPivot));
        System.out.println("Pivot index: " + partition(unsortedForPivot, 0, unsortedForPivot.length - 1));
        System.out.println("Partitioned values: " + Arrays.toString(unsortedForPivot) + "\n");

        final int[] unsorted = {3, 6, 2, 1, 8, 7, 3, 5};
        System.out.println("Initial values: " + Arrays.toString(unsorted));
        sort(unsorted);
        System.out.println("Sorted values: " + Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsortedValues) {
        quickSort(unsortedValues, 0, unsortedValues.length - 1);
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
        int i, j;
        i = left + 1;
        j = left;
        while (i <= right) {
            if (unsorted[i] <= pivot) {
                j++;
                swap(unsorted, i, j);
            }
            i++;
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
