package com.my.algorithms.week1.fourth;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchTask2 {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final int[] values = parse(scanner.nextLine().split(" "));
        final int[] searchedValues = parse(scanner.nextLine().split(" "));
        scanner.close();

        // print(values);
        // print(searchedValues);

        final int[] result = new int[searchedValues.length];
        int index = 0;
        for (int searchedValue : searchedValues) {
            result[index] = search(values, searchedValue);
            index++;
        }

        print(result);
    }

    private static int[] parse(String[] stringValues) {
        final int size = Integer.parseInt(stringValues[0]);
        final int[] values = new int[size];
        int index = 0;
        for (final String value : Arrays.copyOfRange(stringValues, 1, size + 1)) {
            values[index] = Integer.parseInt(value);
            index++;
        }
        return values;
    }

    private static void print(int[] values) {
        final StringBuilder valuesStringBuilder = new StringBuilder(20000);
        for (int i : values) {
            valuesStringBuilder.append(i).append(' ');
        }
        valuesStringBuilder.deleteCharAt(valuesStringBuilder.lastIndexOf(" "));
        System.out.println(valuesStringBuilder.toString());
    }

    public static int search(final int[] values, final int searchedValue) {
        int left = 0;
        int right = values.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (values[middle] == searchedValue) {
                return middle + 1;
            } else if (values[middle] > searchedValue) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
