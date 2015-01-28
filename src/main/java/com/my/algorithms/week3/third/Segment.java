package com.my.algorithms.week3.third;

public class Segment {

    public static void main(String[] args) {
        System.out.println(round(3));
        System.out.println(round(19));
        System.out.println(round(79));
        System.out.println(round(3445));
        System.out.println(round(59994949));
    }

    public static int round(final int value) {
        return 2 << (value == 0 ? 0 : 32 - Integer.numberOfLeadingZeros(value - 1));
    }
}
