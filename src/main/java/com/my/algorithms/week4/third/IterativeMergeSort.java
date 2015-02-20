package com.my.algorithms.week4.third;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import static com.my.algorithms.tools.Arrays.merge;

/**
 * Iterative merge sort algorithm implementation.
 */
public class IterativeMergeSort {

    public static void main(final String[] args) {
        final int[] unsorted = {2, 3, 2, 1, 2};
        System.out.println("Initial values: " + Arrays.toString(unsorted));
        sort(unsorted);
        System.out.println("Sorted values: " + Arrays.toString(unsorted));
    }

    public static void sort(final int[] unsorted) {
        final Deque<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < unsorted.length; i++) {
            queue.offer(Arrays.copyOfRange(unsorted, i, i + 1));
        }
        while (queue.size() > 1) {
            queue.offer(merge(queue.poll(), queue.poll()));
        }
        System.arraycopy(queue.poll(), 0, unsorted, 0, unsorted.length);
    }
}
