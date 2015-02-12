package com.my.algorithms.week4.third;

import java.util.Arrays;
import static com.my.algorithms.tools.Arrays.merge;

/**
 * Merge sort algorithm implementation.
 */
public class MergeSort {

    public static void main(final String[] args) {

        final int[] firstForMerge = {1, 2, 5};
        final int[] secondForMerge = {3, 4, 6, 8};
        System.out.println("First array for merge: " + Arrays.toString(firstForMerge));
        System.out.println("Second array for merge: " + Arrays.toString(secondForMerge));
        System.out.println("Merged array: " + Arrays.toString(merge(firstForMerge, secondForMerge)) + "\n");

        final int[] merged = new int[firstForMerge.length + secondForMerge.length];
        merge(firstForMerge, secondForMerge, merged, 0);
        System.out.println("Merged array (sophisticated): " + Arrays.toString(merged) + "\n");

        System.out.println("Merge of unequaled arrays ({1} and {}): " + Arrays.toString(merge(new int[] {1}, new int[]{})) + "\n");

        final int[] unsorted = {2, 3, 2, 1, 2};
        System.out.println("Initial values: " + Arrays.toString(unsorted));
        sort(unsorted);
        System.out.println("Sorted values: " + Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsorted) {
        mergeSort(unsorted, 0, unsorted.length - 1);
    }

    private static void mergeSort(final int[] unsorted, final int left, final int right) {
        if (left >= right) {
            return;
        }
        final int middle = (left + right) / 2;
        mergeSort(unsorted, left, middle);
        mergeSort(unsorted, middle + 1, right);
        final int[] leftArray = Arrays.copyOfRange(unsorted, left, middle);
        final int[] rightArray = Arrays.copyOfRange(unsorted, middle + 1, right);
        merge(leftArray, rightArray, unsorted, left);
    }

}
