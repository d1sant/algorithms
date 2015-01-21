package com.my.algorithms.week1;

/**
 * Binary search algorithm implementation.
 */
public class BinarySearch {

    public static void main(String[] args) {
        final int[] values = {1, 1, 1, 2, 6, 8, 8, 10, 11, 15, 15, 17, 19, 24, 30}; // 15 elements
        System.out.println("Index of 1: " + search(values, 1));
        System.out.println("Index of 7: " + search(values, 7));
        System.out.println("Index of 9: " + search(values, 9));
        System.out.println("Index of 10: " + search(values, 10));
        System.out.println("Index of 11: " + search(values, 11));
        System.out.println("Index of 15: " + search(values, 15));
        System.out.println("Index of 20: " + search(values, 20));
        System.out.println("Index of 30: " + search(values, 30));
    }

    public static int search(final int[] values, final int searchedValue) {
        int left = 0;
        int right = values.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (values[middle] == searchedValue) {
                return middle;
            } else if (values[middle] > searchedValue) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
