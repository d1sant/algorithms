package com.my.algorithms.week1.second;

import java.util.Scanner;

/**
 * Calculates last number of defined fibonacci.
 */
public class FibonacciTask1 {

    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(fib(n));
    }

    public static long fib(final int n) {
        final long result;
        if (n <= 1) {
            result = n;
        } else {
            long fib0 = 0;
            long fib1 = 1;
            long fib = 0;
            for (int i = 2; i <= n; i++) {
                fib = fib1 + fib0;
                fib0 = fib1;
                fib1 = fib;
            }
            result = fib;
        }
        return result;
    }
}
