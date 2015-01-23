package com.my.algorithms.week2.second;

import java.math.BigInteger;

/**
 * Karatsuba multiplication algorithm
 */
public class KaratsubaMultiplication {

    public static void main(final String[] args) {

        // long to bytes and back
        System.out.println(toLong(toBytes(87774)));
        System.out.println(toLong2(toBytes(87774)));

        // rounding Up and Down
        System.out.println(roundUp(3, 2) + roundDown(3, 2));
        System.out.println(roundUp(19, 2) + roundDown(19, 2));
        System.out.println(roundDown(33333, 2) + roundUp(33333, 2));

        // rounding mixed with intrinsic rounding
        System.out.println(roundUp(9, 2) + 9 / 2); // java does round down as default behaviour
        System.out.println(9 / 2 + roundDown(9, 2)); // incorrect

        byte b = 4;
        System.out.println(b << 1);

        byte[] bytes = toBytes(128);
        System.out.println("length: " + bytes.length);
        final StringBuilder before = new StringBuilder();
        final StringBuilder after = new StringBuilder();
        byte rest = 0;
        for (int i = 0; i < bytes.length; i++) {
            before.append(bytes[i] + " ");
            final short updated = (short) (bytes[i] << 1);
            if (updated > 127) {
                rest = (byte) (updated - 127);
                bytes[i] = 127;
            } else if ((updated + rest) > 127) {
                rest = (byte) (updated + rest - 127);
                bytes[i] = 127;
            } else {
                bytes[i] = (byte) updated;
                rest = 0;
            }
            after.append(bytes[i] + " ");
        }
        System.out.println("Before: " + before.toString());
        System.out.println("After: " + after.toString());
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
    @Deprecated
    private static long toLong2(final byte[] bytes) {
        long result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += ((long) bytes[i] & 0xffL) << (8 * i);
        }
        return result;
    }

    private static int roundUp(final int num, final int divisor) {
        return (num + divisor - 1) / divisor;
    }

    /**
     * Useless implementation cause java does it by default
     * @param num values to divide
     * @param divisor divisor value
     * @return rounded by down value after division
     */
    @Deprecated
    private static int roundDown(final int num, final int divisor) {
        return (num + divisor - 2) / divisor;
    }
}
