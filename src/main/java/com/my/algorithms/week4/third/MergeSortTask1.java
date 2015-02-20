package com.my.algorithms.week4.third;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSortTask1 {

    private static long counter = 0;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int size = scanner.nextInt();
        final int[] unsorted = new int[size];
        scanner.nextLine();
        int index = 0;
        for (final String value : scanner.nextLine().split(" ")) {
            unsorted[index] = Integer.parseInt(value);
            index++;
        }
        System.out.println(count(unsorted));
    }

    private static long count(int[] unsorted) {
        mergeSort(unsorted, 0, unsorted.length - 1);
        return counter;
    }

    private static void mergeSort(final int[] unsorted, final int left, final int right) {
        if (left >= right) {
            return;
        }
        final int middle = (left + right) / 2;
        mergeSort(unsorted, left, middle);
        mergeSort(unsorted, middle + 1, right);
        final int[] leftArray = Arrays.copyOfRange(unsorted, left, middle + 1);
        final int[] rightArray = Arrays.copyOfRange(unsorted, middle + 1, right + 1);
        merge(leftArray, rightArray, unsorted, left);
    }

    private static int[] merge(final int[] first, final int[] second) {
        final int[] result = new int[first.length + second.length];
        int i = 0, j = 0, k = 0;
        while (i < first.length && j < second.length) {
            if (first[i] <= second[j]) {
                result[k++] = first[i++];
            } else {
                result[k++] = second[j++];
                counter += first.length - i;
            }
        }
        while (i < first.length) {
            result[k++] = first[i++];
        }
        while (j < second.length) {
            result[k++] = second[j++];
        }
        return result;
    }

    private static void merge(final int[] first, final int[] second, final int[] dest, final int destIndex) {
        System.arraycopy(merge(first, second), 0, dest, destIndex, first.length + second.length);
    }
}
