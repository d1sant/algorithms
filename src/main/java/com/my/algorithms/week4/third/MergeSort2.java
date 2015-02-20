package com.my.algorithms.week4.third;

import java.util.Arrays;

/**
 * Merge sort algorithm implementation. Another implementation.
 */
public class MergeSort2 {

    public static void main(final String[] args) {

        final int[] valuesForMerge = {1, 2, 5, 7, 2, 6, 8};
        final int number = valuesForMerge.length;
        System.out.println("Initial array for merge: " + Arrays.toString(valuesForMerge));
        merge(valuesForMerge, 0, (number - 1) / 2, number - 1);
        System.out.println("Merged array: " + Arrays.toString(valuesForMerge) + "\n");

        final int[] unsorted = {2, 3, 2, 1, 2};
        System.out.println("Initial values: " + Arrays.toString(unsorted));
        sort(unsorted);
        System.out.println("Sorted values: " + Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsorted) {
        mergeSort(unsorted, 0, unsorted.length - 1);
    }

    private static void mergeSort(final int[] unsorted, final int left, final int right) {
        System.out.println(left + ", " + right);
        if (left < right) {
            final int middle = left + (right - left) / 2;
            mergeSort(unsorted, left, middle);
            mergeSort(unsorted, middle + 1, right);
            System.out.println("For merge (" + left + ":" + middle + ":" + right + "): " + Arrays.toString(unsorted));
            merge(unsorted, left, middle, right);
        }
    }

    private static void merge(final int[] array, final int leftIndex, final int middleIndex, final int rightIndex) {
        final int[] helper = new int[array.length];
        System.arraycopy(array, leftIndex, helper, leftIndex, rightIndex - leftIndex + 1);
        int i = leftIndex, j = middleIndex + 1, k = leftIndex;
        while (i <= middleIndex && j <= rightIndex) {
            System.out.println("[i: " + i + ", j: " + j + ", k:" + k + "]   h[i]=" + helper[i] + ", h[j]=" + helper[j]);
            array[k++] = helper[i] < helper[j] ? helper[i++] : helper[j++];
            System.out.println("helper: " + java.util.Arrays.toString(helper) + " array: " + java.util.Arrays.toString(array) + "\n");
        }
        while (i <= middleIndex) {
            array[k++] = helper[i++];
        }
    }
}
