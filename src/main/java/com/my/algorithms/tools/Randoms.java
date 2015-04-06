package com.my.algorithms.tools;

import java.util.Random;

/**
 * Auxiliary API for working random values.
 */
public class Randoms {

    private static final Random random = new Random();

    public static int randInt(final int min, final int max) {
        return random.nextInt(max - min) + min;
    }
}
