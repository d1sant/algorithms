package com.my.algorithms.week1;

/**
 * Linear search implementation.
 */
public class LinearSearch {

    public static void main(String[] args) {
        final int[] values = {1, 3, 2, 1, 6, 5, 0, 8};
        System.out.println("Index of 8: " + search(values, 8));
        System.out.println("Index of 9: " + search(values, 9));
        System.out.println("Index of 4: " + search(values, 4));
        System.out.println("Index of 1: " + search(values, 1));
    }

    public static int search(int[] values, int searchedValue) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == searchedValue) {
                return i;
            }
        }
        return -1;
    }
}
