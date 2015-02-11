package com.my.algorithms.tools;

public class Math {

    /**
     * Useless implementation cause java does it by default
     * @param num values to divide
     * @param divisor divisor value
     * @return rounded by down value after division
     */
    @Deprecated
    public static int roundDown(final int num, final int divisor) {
        return (num + divisor - 2) / divisor;
    }

    public static int roundUp(final int num, final int divisor) {
        return (num + divisor - 1) / divisor;
    }
}
