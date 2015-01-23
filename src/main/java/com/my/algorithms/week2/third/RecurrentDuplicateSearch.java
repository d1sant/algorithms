package com.my.algorithms.week2.third;

import java.util.HashMap;
import java.util.Map;

/**
 * Calculates duplicated values in defined array.
 */
public class RecurrentDuplicateSearch {

    public static void main(final String[] args) {
        final int[] values = {2, 3, 9, 2, 2};
        System.out.println("Result: " + find(values));
        System.out.println("Result silly: " + findSilly(values));
    }

    private static int find(final int[] values) {
        return -1;
    }

    private static int findSilly(final int[] values) {
        final int nd2 = values.length / 2;
        final Map<Integer, Integer> statistic = new HashMap<Integer, Integer>();
        for (int value : values) {
            final Integer count = statistic.get(value);
            statistic.put(value, count != null ? count + 1 : 1);
        }
        for (final Integer counter : statistic.values()) {
            if (counter > nd2) {
                return 1;
            }
        }
        return 0;
    }
}
