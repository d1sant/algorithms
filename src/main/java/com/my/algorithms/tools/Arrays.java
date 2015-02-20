package com.my.algorithms.tools;

/**
 * Auxiliary API for working with arrays.
 */
public class Arrays {

    public static void swap(final int[] array, final int fromIndex, final int toIndex) {
        final Integer to = array[toIndex];
        array[toIndex] = array[fromIndex];
        array[fromIndex] = to;
    }

    public static int[] merge(final int[] first, final int[] second) {
        final int[] result = new int[first.length + second.length];
        int i = 0, j = 0, k = 0;
        while (i < first.length && j < second.length) {
            result[k++] = first[i] < second[j] ? first[i++] : second[j++];
        }
        while (i < first.length) {
            result[k++] = first[i++];
        }
        while (j < second.length) {
            result[k++] = second[j++];
        }
        return result;
    }

    public static void merge(final int[] first, final int[] second, final int[] dest, final int destIndex) {
        System.arraycopy(merge(first, second), 0, dest, destIndex, first.length + second.length);
    }

    public static void merge(final int[] array, final int leftIndex, final int middleIndex, final int rightIndex) {
        final int[] helper = new int[array.length];
        System.arraycopy(array, leftIndex, helper, leftIndex, rightIndex - leftIndex + 1);
        int i = leftIndex, j = middleIndex + 1, k = leftIndex;
        while (i <= middleIndex && j <= rightIndex) {
            array[k++] = helper[i] < helper[j] ? helper[i++] : helper[j++];
        }
        while (i <= middleIndex) {
            array[k++] = helper[i++];
        }
    }
}
