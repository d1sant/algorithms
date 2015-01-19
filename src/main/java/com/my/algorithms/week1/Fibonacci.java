package com.my.algorithms.week1;

/**
 * Simple implementation of Fibonacci algorithm.
 * Be aware that it's very slow after numbers greater than 100.
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("Fib 0: " + fib(0));
        System.out.println("Fib 1: " + fib(1));
        System.out.println("Fib 2: " + fib(2));
        System.out.println("Fib 3: " + fib(3));
        System.out.println("Fib 4: " + fib(4));
        System.out.println("Fib 5: " + fib(5));
        // System.out.println("Fib 100: " + fib(100)); // too slow
    }

    public static long fib(int n) {
        return n <= 1 ? n : fib(n - 1) + fib(n - 2);
    }
}
