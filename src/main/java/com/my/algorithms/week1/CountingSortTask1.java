package com.my.algorithms.week1;

import java.util.Scanner;

public class CountingSortTask1 {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] unsorted = new int[n];
        scanner.nextLine();
        int index = 0;
        for (final String value : scanner.nextLine().split(" ")) {
            unsorted[index] = Integer.parseInt(value);
            index++;
        }
        scanner.close();
        sort(unsorted, 10);
        final StringBuilder stringBuilder = new StringBuilder(20000);
        for (int i : unsorted) {
            stringBuilder.append(i).append(' ');
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        System.out.println(stringBuilder.toString());
    }

    public static void sort(final int[] unsortedValues, final int maxValue) {
        final int[] counters = new int[maxValue];
        for (int i = 0; i < maxValue; i++) {
            counters[i] = 0;
        }
        for (final int value : unsortedValues) {
            counters[value - 1] = counters[value - 1] + 1;
        }
        int index = 0;
        for (int counterIndex = 0; counterIndex < maxValue; counterIndex++) {
            for (int valueIndex = 0; valueIndex < counters[counterIndex]; valueIndex++) { // TODO refactor
                unsortedValues[index] = counterIndex + 1;
                index++;
            }
        }
    }
}
