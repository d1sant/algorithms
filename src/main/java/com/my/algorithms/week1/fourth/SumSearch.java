package com.my.algorithms.week1.fourth;

import com.my.algorithms.domain.dto.Pair;

/**
 * Sum search algorithm implementation.
 */
public class SumSearch {

    public static void main(final String[] args) {
        final int[] values = {1, 1, 5, 3, 6, 4, 8, 9, 2};
        System.out.println("Indexes for sum of 10 " + search(values, 10));
        System.out.println("Indexes for sum of 12 " + search(values, 12));
        System.out.println("Indexes for sum of 15 " + search(values, 15));
        System.out.println("Indexes for sum of 16 " + search(values, 16));
        System.out.println("Indexes for sum of 21 " + search(values, 21));
    }

    public static Pair<Integer, Integer> search(final int[] values, final int sum) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] + values[j] == sum) {
                    return new Pair<Integer, Integer>(i, j);
                }
            }
        }
        return new Pair<Integer, Integer>(-1, -1);
    }
}
