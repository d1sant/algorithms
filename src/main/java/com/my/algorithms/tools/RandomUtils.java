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

    public static int[] shuffle(final int[] t) {
        return shuffle(t, t.length);
    }
    public static int[] shuffle(final int[] t, final int limit) {
        final int[] retVal = new int[t.length];
        System.arraycopy(t, 0, retVal, 0, t.length);

        final int lim = Math.min(retVal.length, limit);
        for (int i = 0; i < lim; ++i) {
            final int modulo = lim -i;
            final int j = rnd.nextInt(modulo);
            final int x = retVal[i];
            final int xx= retVal[i+j];
            retVal[i] = xx;
            retVal[i+j] = x;
        }
        return retVal;
    }

    public static Integer[] shuffle(final Integer[] t) {
        return shuffle(t, t.length);
    }
    public static Integer[] shuffle(final Integer[] t, final int limit) {
        final Integer[] retVal = new Integer[t.length];
        System.arraycopy(t, 0, retVal, 0, t.length);

        final int lim = Math.min(retVal.length, limit);
        for (int i = 0; i < lim; ++i) {
            final int modulo = lim - i;
            final int j = rnd.nextInt(modulo);
            final Integer x = retVal[i];
            final Integer xx= retVal[i+j];
            retVal[i] = xx;
            retVal[i+j] = x;
        }
        return retVal;
    }
}
