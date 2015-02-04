package com.my.algorithms.week4.fourth;

/**
 * Abstract implementation of quick sort algorithm.
 */
abstract class AbstractQuickSort {

    /**
     * Defines and swaps pivot element to begin of array.
     */
    abstract protected void definePivot(final int[] unsorted, final int left, final int right);

    protected void quickSort(final int[] unsorted, final int left, final int right) {
        if (left >= right) {
            return;
        }
        final int pivotIndex = partition(unsorted, left, right);
        quickSort(unsorted, left, pivotIndex - 1);
        quickSort(unsorted, pivotIndex + 1, right);
    }

    protected int partition(final int[] unsorted, final int left, final int right) {
        definePivot(unsorted, left, right);
        final int pivot = unsorted[left];
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

    protected static void swap(final int[] array, final int fromIndex, final int toIndex) {
        final Integer to = array[toIndex];
        array[toIndex] = array[fromIndex];
        array[fromIndex] = to;
    }
}
