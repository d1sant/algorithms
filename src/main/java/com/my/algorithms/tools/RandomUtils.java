package com.my.algorithms.tools;

import java.lang.*;
import java.util.Arrays;

/**
 * Auxiliary utils for working with random values along with generation.
 */
public class RandomUtils {

    public static void main(final String[] args) {
        // printStatTillMillion();
        printTillTen();
    }

    private static void printStatTillMillion() {
        final int[] result = {0, 0, 0, 0};
        for (int i = 0; i < 1000000; i++) {
            final int index = randomIndexX(new double[]{7, 6, 0, 0});
            result[index] += 1;
        }
        System.out.println(Arrays.toString(result));
    }

    private static void printTillTen() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + randomIndexX(new double[]{7, 6, 0, 0}));
        }
    }

    public static final int randomIndexX(final double[] p) {
        double tmpsum, sump;
        sump = tmpsum = 0.0;
        for (double d : p) {
            sump += d;
        }
        final double rnd = java.lang.Math.random() * sump;
        for (int i = 0; i < p.length; ++i) {
            if (p[i] > 0.0) {
                if ((tmpsum <= rnd) && rnd < (tmpsum + p[i])) {
                    return i;
                }
                tmpsum += p[i];
            }
        }
        return 0;
    }
}
