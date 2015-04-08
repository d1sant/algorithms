package com.my.algorithms.tools;

import java.lang.Math;
import java.util.Arrays;
import java.util.Random;

/**
 * Auxiliary utils for working with random values along with generation.
 */
public class RandomUtils {

    private static final Random rnd = new Random();

    public static void main(final String[] args) {
        // printStatTillMillion();
        // printTillTen();
    }

    public static void printStatTillMillion() {
        final int[] result = {0, 0, 0, 0};
        for (int i = 0; i < 1000000; i++) {
            final int index = randomIndexX(new double[]{7, 6, 0, 0});
            result[index] += 1;
        }
        System.out.println(Arrays.toString(result));
    }

    public static void printTillTen() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + randomIndexX(new double[]{7, 6, 0, 0}));
        }
    }

    public static int randomIndexX(final double[] p) {
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

    public static int[] shuffle(int[] t) {
        return shuffle(t, t.length);
    }
    public static int[] shuffle(int[] t, int limit) {
        int[] retVal = new int[t.length];
        System.arraycopy(t, 0, retVal, 0, t.length);

        int lim = Math.min(retVal.length, limit);
        for (int i = 0; i < lim; ++i) {
            int modulo = lim -i;
            int j = rnd.nextInt(modulo);
            int x = retVal[i];
            int xx= retVal[i+j];
            retVal[i] = xx;
            retVal[i+j] = x;
        }
        return retVal;
    }

    public static Integer[] shuffle(Integer[] t) {
        return shuffle(t, t.length);
    }
    public static Integer[] shuffle(Integer[] t, int limit) {
        Integer[] retVal = new Integer[t.length];
        System.arraycopy(t, 0, retVal, 0, t.length);

        int lim = Math.min(retVal.length, limit);
        for (int i = 0; i < lim; ++i) {
            int modulo = lim -i;
            int j = rnd.nextInt(modulo);
            int x = retVal[i];
            int xx= retVal[i+j];
            retVal[i] = xx;
            retVal[i+j] = x;
        }
        return retVal;
    }
}
