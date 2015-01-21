package com.my.algorithms.domain.dto;

/**
 * Container class for two values.
 * @param <F> first value
 * @param <S> second value
 */
public class Pair<F, S> {

    public final F first;
    public final S second;

    public Pair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "{i=" + first + ", j=" + second + "}";
    }
}
