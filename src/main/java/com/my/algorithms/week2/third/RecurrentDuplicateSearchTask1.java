package com.my.algorithms.week2.third;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RecurrentDuplicateSearchTask1 {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] values = new int[n];
        scanner.nextLine();
        int index = 0;
        for (final String value : scanner.nextLine().split(" ")) {
            values[index] = Integer.parseInt(value);
            index++;
        }
        scanner.close();
        System.out.println(findSilly(values));
    }

    private static int findSilly(final int[] values) {
        final int nd2 = values.length / 2;
        final Map<Integer, Integer> statistic = new HashMap<Integer, Integer>();
        for (int value : values) {
            final Integer count = statistic.get(value);
            statistic.put(value, count != null ? count + 1 : 1);
        }
        for (Integer counter : statistic.values()) {
            if (counter > nd2) {
                return 1;
            }
        }
        return 0;
    }
}
