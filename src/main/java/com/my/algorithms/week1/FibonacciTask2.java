package com.my.algorithms.week1;

import java.util.Scanner;

public class FibonacciTask2 {

    public static void main(final String[] args) {
        final Scanner s = new Scanner(System.in);
        final long n = s.nextLong();
        System.out.println(fibLastNum(n));
    }

    public static int fibLastNum(final long n) {
        final int result;
        if (n <= 1) {
            result = (int) n;
        } else {
            int fib0 = 0;
            int fib1 = 1;
            int fibLastNum = 0;
            for (int i = 2; i <=n; i++) {
                fibLastNum = (fib1 + fib0) % 10;
                fib0 = fib1;
                fib1 = fibLastNum;
            }
            result = fibLastNum % 10;
        }
        return result;
    }
}
