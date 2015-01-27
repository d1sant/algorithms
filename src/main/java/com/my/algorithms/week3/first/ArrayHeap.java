package com.my.algorithms.week3.first;

import java.util.Arrays;

/**
 * Heap algorithm implementation based on array storage.
 * In order to extend intrinsic array we need create new one with doubled size and copy elements from old array.
 */
public class ArrayHeap implements Heap<Integer> {

    private static final int INITIAL_HEAP_SIZE = 100;
    private static final int RESIZE_FACTOR = 2;

    private int[] heap = new int[INITIAL_HEAP_SIZE];
    private int lastIndex = -1;

    public static void main(String[] args) {
        int[] values = {1, 2, 3};
        System.out.println("Length: " + values.length);
        System.out.println(Arrays.toString(values) + "\n");

        values = Arrays.copyOf(values, values.length * 2);
        System.out.println("New length: " + values.length);
        System.out.println(Arrays.toString(values));
    }

    @Override
    public void insert(Integer value) {
        final int newLastIndex = lastIndex + 1;
        if (newLastIndex < heap.length) {
            heap[newLastIndex] = value;
            lastIndex = newLastIndex;
            if (heap[newLastIndex / 2] > value) {
                siftUp(newLastIndex);
            }
        } else {
            heap = Arrays.copyOf(heap, heap.length * RESIZE_FACTOR);
        }
    }

    @Override
    public void remove(int index) {
        // TODO implement me
    }

    @Override
    public Integer get() {
        return heap[0];
    }

    @Override
    public Integer extract() {
        return null;  // TODO implement me
    }

    @Override
    public void change(int index, Integer value) {
        // TODO implement me
    }

    private int siftUp(final int index) {
        return -1; // TODO implement me
    }

    private int siftDown(final int index) {
        return -1; // TODO implement me
    }
}
