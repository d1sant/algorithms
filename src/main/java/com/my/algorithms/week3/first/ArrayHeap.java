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

        final int[] valuesSiftUp = {1, 2, 3, 5, 8, 2};
        final ArrayHeap heapSiftUp = new ArrayHeap(valuesSiftUp);
        System.out.println("Initial heap: " + Arrays.toString(heapSiftUp.getHeap()));
        System.out.println("Sifted up to index: " + heapSiftUp.siftUp(5));
        System.out.println("Sifted up heap: " + Arrays.toString(heapSiftUp.getHeap()) + "\n");

        System.out.println("Initial heap: " + Arrays.toString(heapSiftUp.getHeap()));
        System.out.println("Inserted index: " + heapSiftUp.insert(2));
        System.out.println("Inserted index: " + heapSiftUp.insert(12));
        System.out.println("Inserted heap: " + Arrays.toString(heapSiftUp.getHeap()) + "\n");

        final ArrayHeap heapInsert = new ArrayHeap(15);
        for (int i = 1; i < 15; i++) {
            heapInsert.insert(i);
        }
        System.out.println("Inserted heap: " + Arrays.toString(heapInsert.getHeap()) + "\n");

        final int[] valuesSiftDown = {12, 2, 3, 5, 8, 10};
        final ArrayHeap heapSiftDown = new ArrayHeap(valuesSiftDown);
        System.out.println("Initial heap: " + Arrays.toString(heapSiftDown.getHeap()));
        System.out.println("Sifted down to index: " + heapSiftDown.siftDown(0));
        System.out.println("Sifted down heap: " + Arrays.toString(heapSiftDown.getHeap()) + "\n");

        final int[] valuesExtract = {1, 2, 3, 4, 5, 6, 7};
        final ArrayHeap heapExtract = new ArrayHeap(valuesExtract);
        System.out.println("Initial heap: " + Arrays.toString(heapExtract.getHeap()));
        for (int i = 1; i <= 7; i++) {
            System.out.println("Extracted value: " + heapExtract.extract());
        }
        System.out.println("Extracted heap length: " + heapExtract.lastIndex);
        System.out.println("Extracted heap: " + Arrays.toString(heapExtract.getHeap()) + "\n");
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
        final Integer result = heap[0];
        heap[0] = heap[lastIndex];
        heap[lastIndex] = 0; // TODO Change it to null after modifying base storage
        lastIndex--;
        if (heap[0] > heap[1] || heap[0] > heap[2]) {
            siftDown(0);
        }
        return result;
    }

    @Override
    public void change(final int index, final Integer value) {
        // TODO implement me
    }

    private int siftUp(final int index) {
        int parentIndex = (index - 1) / 2;
        int childIndex = index;
        while (parentIndex >= 0 && heap[parentIndex] > heap[childIndex]) {
            swap(parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        return childIndex;
    }

    private int siftDown(final int index) {
        int parentIndex = index;
        while (true) {
            int childIndex = parentIndex * 2 + 1;
            if (childIndex > lastIndex) {
                break;
            }
            if (childIndex + 1 <= lastIndex) {
                childIndex = (heap[childIndex] <= heap[(childIndex + 1)]) ? childIndex : childIndex + 1;
            }
            if (heap[parentIndex] <= heap[childIndex]) {
                break;
            }
            swap(parentIndex, childIndex);
            parentIndex = childIndex;
        }
        return parentIndex;
    }

    private void swap(final int fromIndex, final int toIndex) {
        final Integer to = heap[toIndex];
        heap[toIndex] = heap[fromIndex];
        heap[fromIndex] = to;
    }

    private int[] getHeap() {
        return heap;
    }
}
