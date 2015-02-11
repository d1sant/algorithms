package com.my.algorithms.week4.second;

import java.util.Scanner;

public class HeapSortTask1 {

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
        sort(unsorted);
        for (int value : unsorted) {
            System.out.print(value + " ");
        }
    }

    public static void sort(final int[] unsorted) {
        buildMaxHeap(unsorted);
        int size = unsorted.length;
        for (int i = (unsorted.length - 1); i >= 1; i--) {
            swap(unsorted, i, 0);
            size--;
            siftDownMin(unsorted, 0, size - 1);
        }
    }

    private static void buildMaxHeap(int[] unsorted) {
        for (int index = roundUp(unsorted.length, 2); index >= 0; index--) {
            siftDownMin(unsorted, index, unsorted.length - 1);
        }
    }

    public static int siftDownMin(final int[] heap, final int index, final int lastIndex) {
        return siftDown(heap, index, lastIndex, new Comparable() {
            @Override
            public boolean compare(int first, int second) {
                return first >= second;
            }
        });
    }

    private static int siftDown(final int[] heap, final int index, final int lastIndex, final Comparable operator) {
        int parentIndex = index;
        while (true) {
            int childIndex = parentIndex * 2 + 1;
            if (childIndex > lastIndex) {
                break;
            }
            if (childIndex + 1 <= lastIndex) {
                childIndex = (operator.compare(heap[childIndex], heap[(childIndex + 1)])) ? childIndex : childIndex + 1;
            }
            if (operator.compare(heap[parentIndex], heap[childIndex])) {
                break;
            }
            swap(heap, parentIndex, childIndex);
            parentIndex = childIndex;
        }
        return parentIndex;
    }

    private static void swap(final int[] array, final int fromIndex, final int toIndex) {
        final Integer to = array[toIndex];
        array[toIndex] = array[fromIndex];
        array[fromIndex] = to;
    }

    private static int roundUp(final int num, final int divisor) {
        return (num + divisor - 1) / divisor;
    }

    private interface Comparable {
        boolean compare(final int first, final int second);
    }
}
