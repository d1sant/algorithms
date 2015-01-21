package com.my.algorithms.week1.second;

/**
 * Simple implementation of Fibonacci algorithm.
 * Be aware that it's very slow after numbers greater than 100.
 */
public class Fibonacci extends AbstractFibonacci {

    public static void main(final String[] args) {
        final Fibonacci fib = new Fibonacci();
        fib.print(0);
        fib.print(1);
        fib.print(2);
        fib.print(3);
        fib.print(4);
        fib.print(5);
        fib.print(20);
        // fib.print(100); // too slow
    }

    @Override
    public long fib(final int n) {
        return n <= 1 ? n : fib(n - 1) + fib(n - 2);
    }
}
