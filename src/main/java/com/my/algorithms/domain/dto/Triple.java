package com.my.algorithms.domain.dto;

/**
 * Container class for three values.
 * @param <F> first value
 * @param <S> second value
 * @param <T> third value
 */
public class Triple<F, S, T> {

    public final F first;
    public final S second;
    public final T third;

    public Triple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return "{i=" + first + ", j=" + second + ", k=" + third + "}";
    }
}
