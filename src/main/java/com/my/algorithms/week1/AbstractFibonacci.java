package com.my.algorithms.week1;

/**
 * Abstract helper for Fibonacci algorithms implementation.
 */
public abstract class AbstractFibonacci {

    /**
     * Prints out fibonacci number.
     * @param n input value
     */
    protected void print(final int n) {
        System.out.println("Fib " + n + ": " + fib(n));
    }

    /**
     * Calculates fibonacci number.
     * @param n input value
     * @return fibonacci value
     */
    abstract long fib(final int n);
}
