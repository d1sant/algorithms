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

    public ArrayHeap() {

    }

    public ArrayHeap(final int size) {
        this.heap = new int[size];
    }

    private ArrayHeap(final int[] heap) {
        this.heap = heap;
        this.lastIndex = heap.length - 1;
    }

    public static void main(final String[] args) {
        int[] values = {1, 2, 3};
        System.out.println("Length: " + values.length);
        System.out.println(Arrays.toString(values) + "\n");

        values = Arrays.copyOf(values, values.length * 2);
        System.out.println("New length: " + values.length);
        System.out.println(Arrays.toString(values) + "\n");

        int[] heapValues = {1, 2, 3, 5, 8, 2};
        final ArrayHeap heap = new ArrayHeap(heapValues);
        System.out.println("Initial up heap: " + Arrays.toString(heap.getHeap()));
        System.out.println("Sifted up to index: " + heap.siftUp(5));
        System.out.println("Sifted up heap: " + Arrays.toString(heap.getHeap()) + "\n");

        System.out.println("Before insert: " + Arrays.toString(heap.getHeap()));
        System.out.println("Inserted index: " + heap.insert(2));
        System.out.println("Inserted index: " + heap.insert(12));
        System.out.println("Inserted heap: " + Arrays.toString(heap.getHeap()) + "\n");

        final ArrayHeap heapForInsert = new ArrayHeap(15);
        for (int i = 1; i < 15; i++) {
            heapForInsert.insert(i);
        }
        System.out.println("Inserted heap: " + Arrays.toString(heapForInsert.getHeap()));
    }

    @Override
    public int insert(final Integer value) {
        final int newLastIndex = lastIndex + 1;
        if (newLastIndex >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * RESIZE_FACTOR);
        }
        heap[newLastIndex] = value;
        lastIndex = newLastIndex;
        return (heap[(newLastIndex - 1) / 2] > value) ? siftUp(newLastIndex) : newLastIndex;
    }

    @Override
    public void remove(final int index) {
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
    public void change(final int index, final Integer value) {
        // TODO implement me
    }

    private int siftUp(final int index) {
        int parentIndex = (index - 1) / 2;
        int childIndex = index;
        while (parentIndex >= 0 && heap[parentIndex] > heap[childIndex]) {
            final Integer parent = heap[parentIndex];
            heap[parentIndex] = heap[childIndex];
            heap[childIndex] = parent;
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        return childIndex;
    }

    private int siftDown(final int index) {
        return -1; // TODO implement me
    }

    private int[] getHeap() {
        return heap;
    }
}
