package com.my.algorithms.week1;

/**
 * Enhanced Fibonacci algorithms. Optimized memory usage (3 variables instead of array of long with size of n).
 */
public class FibonacciEnhanced extends AbstractFibonacci {

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
        fib.print(30);
        fib.print(100);
    }

    @Override
    long fib(int n) {
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
