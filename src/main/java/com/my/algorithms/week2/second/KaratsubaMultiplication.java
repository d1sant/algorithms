package com.my.algorithms.week2.second;

import java.math.BigInteger;

/**
 * Karatsuba multiplication algorithm
 */
public class KaratsubaMultiplication {

    public static void main(String[] args) {

        // long to bytes and back
        System.out.println(toLong(toBytes(87774)));
        System.out.println(toLong2(toBytes(87774)));

        // rounding Up and Down
        System.out.println(roundUp(3, 2) + roundDown(3, 2));
        System.out.println(roundUp(19, 2) + roundDown(19, 2));
        System.out.println(roundDown(33333, 2) + roundUp(33333, 2));

        byte b = 4;
        System.out.println(b << 1);

        byte[] bytes = toBytes(3);
        System.out.println("length: " + bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("-- before:" + bytes[i]);
            bytes[i] = (byte) (bytes[i] << 1);
            System.out.println("-- after:" + bytes[i]);
        }
        System.out.println(toLong(bytes));
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
            final int nUp = roundUp(n, 2);
            final int nDown = roundDown(n, 2);
            final byte[] xl;
            final byte[] xr;
            final byte[] yl;
            final byte[] yr;

            final byte[] p1 = new byte[0]; // multiply(...);
            final byte[] p2 = new byte[0]; // multiply(...);
            final byte[] p3 = new byte[0]; // multiply(...);

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

    private static int roundUp(int num, int divisor) {
        return (num + divisor - 1) / divisor;
    }

    private static int roundDown(int num, int divisor) {
        return (num + divisor - 2) / divisor;
    }
}
