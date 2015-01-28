package com.my.algorithms.week3.second;

/**
 * API for heap implementations.
 * @param <T> stored value type.
 */
public interface Heap<T> {

    /**
     * Inserts element to heap in correct place according to max/min priority (depends on specific implementation)
     * @param value value to be inserted
     * @return index of element in the heap
     */
    int insert(final T value);

    /**
     * Removes element by its index.
     * @param index index of element
     */
    void remove(final int index);

    /**
     * Gets element with max/min priority (depends on specific implementation)
     * @return value object with type of T and relevant priority
     */
    T get();

    /**
     * Extracts max/min (depends on specific implementation) element from heap.
     * @return value object with type of T
     */
    T extract();

    /**
     * Changes priority of element with specified index.
     * @param index index of element to be changed
     * @param value priority of element
     * @return index of element in the heap
     */
    int change(final int index, final T value);
}
