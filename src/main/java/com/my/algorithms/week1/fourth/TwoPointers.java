package com.my.algorithms.week1.fourth;

import com.my.algorithms.domain.dto.Pair;

/**
 * Sum search algorithm implementation based on ordered array using two pointers.
 */
public class TwoPointers {

    public static void main(final String[] args) {
        final int[] values = {1, 1, 3, 4, 6, 8, 8, 10, 11, 15, 17};
        System.out.println("Indexes for sum of 3 " + search(values, 3));
        System.out.println("Indexes for sum of 12 " + search(values, 12));
        System.out.println("Indexes for sum of 15 " + search(values, 15));
        System.out.println("Indexes for sum of 16 " + search(values, 16));
        System.out.println("Indexes for sum of 21 " + search(values, 21));
        System.out.println("Indexes for sum of 30 " + search(values, 30));
    }

    public static Pair<Integer, Integer> search(final int[] values, final int sum) {
        int left = 0;
        int right = values.length - 1;
        while (left < right) {
            if (values[left] + values[right] == sum) {
                return new Pair<Integer, Integer>(left, right);
            } else if (values[left] + values[right] > sum) {
                right--;
            } else {
                left++;
            }
        }
        return new Pair<Integer, Integer>(-1, -1);
    }
}
