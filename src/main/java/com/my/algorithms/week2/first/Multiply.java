package com.my.algorithms.week2.first;

import java.util.Random;

/**
 * Multiplication algorithm with division by two
 */
public class Multiply {

    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = MAX >> 1;

    private static final Random random = new Random();

    public static void main(final String[] args) {

        System.out.println("2 * 2 is " + multiply(2, 2));
        System.out.println("5 * 5 is " + multiply(5, 5));
        System.out.println("5 * 6 is " + multiply(5, 6));

        long now = System.currentTimeMillis();
        benchmark(true, new Multiplication() {
            @Override
            public long multiply(int x, int y) {
                return Multiply.multiply(x, y);
            }
        });
        final long end1 = System.currentTimeMillis() - now;

        now = System.currentTimeMillis();
        benchmark(true, new Multiplication() {
            @Override
            public long multiply(int x, int y) {
                return (long) x * y;
            }
        });
        final long end2 = System.currentTimeMillis() - now;

        System.out.println("Karazuba time spent: " + end1);
        System.out.println("Standart time spent: " + end2);
    }

    private static void benchmark(final boolean print, final Multiplication mult) {
        for (int i = 0; i < 1000; i++) {
            final int x = randInt(MAX, MIN);
            final int y = randInt(MAX, MIN);
            final long result = mult.multiply(x, y);
            if (print) {
                System.out.println(x + " * " + y + " = " + result);
            }
        }
    }

    private static int randInt(final int max, final int min) {
        return random.nextInt(max - min) + min;
    }

    public static long multiply(final int x, final int y) {
        final long result;
        if (y == 0) {
            result = 0;
        } else {
            final long z = multiply(x, y >> 1);
            if (y % 2 == 0) {
                result = z << 1;
            } else {
                result = x + (z << 1);
            }
        }
        return result;
    }

    private static interface Multiplication {
        long multiply(int x, int y);
    }
}
