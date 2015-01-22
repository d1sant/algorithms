package com.my.algorithms.week2.second;

import java.math.BigInteger;

/**
 * Karatsuba multiplication algorithm
 */
public class KaratsubaMultiplication {

    public static void main(String[] args) {
        System.out.println(toLong(toBytes(87774)));
        System.out.println(toLong2(toBytes(87774)));
    }

    public static long multiply(final int x, final int y) {
        return toLong(multiply(toBytes(x), toBytes(y)));
    }

    public static byte[] multiply(final byte[] x, final byte[] y) {
        final byte[] result;
        final int n = Math.max(x.length, y.length);
        if (n == 1) {
            result = new BigInteger(1, x).multiply(new BigInteger(1, y)).toByteArray();
        } else {
            final byte[] xl;
            final byte[] xr;
            final byte[] yl;
            final byte[] yr;

            final byte[] p1 = new byte[0];
            final byte[] p2 = new byte[0];
            final byte[] p3 = new byte[0];

            result = new byte[0]; // p1 << (n / 2) + ((p3 - p1 - p2) << (n / 2)) + p2;
        }
        return result;
    }

    private static byte[] toBytes(final int intValue) {
        final byte[] result = new byte[4];
        result[0] = (byte) (intValue >> 24);
        result[1] = (byte) (intValue >> 16);
        result[2] = (byte) (intValue >> 8);
        result[3] = (byte) intValue;
        return result;
    }

    private static long toLong(final byte[] bytes) {
        long result = 0;
        for (byte aByte : bytes) {
            result = (result << 8) + (aByte & 0xff);
        }
        return result;
    }

    /**
     * Wrong implementation. Created for test purpose.
     * @param bytes array of bytes
     * @return long value
     */
    private static long toLong2(final byte[] bytes) {
        long result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += ((long) bytes[i] & 0xffL) << (8 * i);
        }
        return result;
    }
}
