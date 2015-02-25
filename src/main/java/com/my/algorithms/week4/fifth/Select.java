package com.my.algorithms.week4.fifth;

import java.util.Arrays;

import static com.my.algorithms.tools.Randoms.randInt;

public class Select {

    public static void main(String[] args) {
        final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        System.out.println("Selected value is " + selectInt(values, 8));
    }

    public static int selectInt(final int[] array, int k) {
        final int[] select = select(array, k);
        return select[0];
    }

    public static int[] select(int[] array, int k) {
        if (array.length == 1) {
            return array;
        }
        final int pivot = randInt(0, array.length);
        return select(Arrays.copyOfRange(array, 0, k), k); // TODO fix me
    }
}
