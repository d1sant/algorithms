package com.my.algorithms.week4.fifth;

import java.util.Arrays;

import static com.my.algorithms.tools.Randoms.randInt;

public class RandomSelect {

    public static void main(String[] args) {
        final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    public static int[] select(int[] array, int k) {
        if (array.length == 1) {
            return array;
        }
        final int pivot = randInt(array.length, 0);
        return select(Arrays.copyOfRange(array, 0, k), k); // TODO fix me
    }
}
