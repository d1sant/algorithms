package com.my.algorithms.week1;

/**
 * Implementation of Fibonacci algorithms with array as storage
 * for intermediate numbers in order to get rid of multiple calculation of the same number.
 */
public class FibonacciWithArray extends AbstractFibonacci {

    public static void main(String[] args) {
        final FibonacciWithArray fib = new FibonacciWithArray();
        fib.print(0);
        fib.print(1);
        fib.print(2);
        fib.print(3);
        fib.print(4);
        fib.print(5);
        fib.print(6);
        fib.print(7);
        fib.print(20);
        fib.print(100);
    }

    @Override
    public long fib(final int n) {
        final long result;
        if (n <= 1) {
            result = n;
        } else {
            final long[] fibs = new long[n + 1];
            fibs[0] = 0;
            fibs[1] = 1;
            for (int i = 2; i <= n; i++) {
                fibs[i] = fibs[i - 1] + fibs[i - 2];
            }
            result = fibs[n];
        }
        return result;
    }
}
