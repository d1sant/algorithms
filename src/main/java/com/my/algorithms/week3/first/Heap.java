package com.my.algorithms.week3.first;

/**
 * API for heap implementations.
 * @param <T> stored value type.
 */
public interface Heap<T> {

    /**
     * Extracts max/min (depends on specific implementation) element from heap.
     * @return value object with type of T
     */
    T extract();

    /**
     * Inserts value to heap in correct place according to max/min priority (depends on specific implementation)
     * @param value value to be inserted
     */
    void insert(T value);
}
