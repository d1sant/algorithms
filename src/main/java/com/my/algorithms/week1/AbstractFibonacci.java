package com.my.algorithms.week1;

public abstract class AbstractFibonacci {

    protected void print(final int n) {
        System.out.println("Fib " + n + ": " + fib(n));
    }

    abstract long fib(final int n);
}
