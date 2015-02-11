package com.my.algorithms.week3.second;

import java.util.Arrays;

import static com.my.algorithms.tools.Heaps.siftDownMax;
import static com.my.algorithms.tools.Heaps.siftUp;

/**
 * Heap algorithm implementation based on array storage.
 * In order to extend intrinsic array we need create new one with doubled size and copy elements from old array.
 * TODO refactor it using http://stackoverflow.com/questions/18241192/implement-heap-using-a-binary-tree
 */
public class ArrayHeap implements Heap<Integer> {

    private static final int INITIAL_HEAP_SIZE = 100;
    private static final int RESIZE_FACTOR = 2;

    private int[] heap;
    private int lastIndex;

    public ArrayHeap() {
        this(INITIAL_HEAP_SIZE);
    }

    public ArrayHeap(final int size) {
        this.heap = new int[size];
        this.lastIndex = -1;
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
        System.out.println("Initial heap: " + Arrays.toString(valuesSiftUp));
        System.out.println("Sifted up to index: " + siftUp(valuesSiftUp, 5));
        System.out.println("Sifted up heap: " + Arrays.toString(valuesSiftUp) + "\n");

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
        System.out.println("Initial heap: " + Arrays.toString(valuesSiftDown));
        System.out.println("Sifted down to index: " + siftDownMax(valuesSiftDown, 0, valuesSiftDown.length - 1));
        System.out.println("Sifted down heap: " + Arrays.toString(valuesSiftDown) + "\n");

        final int[] valuesExtract = {1, 2, 3, 4, 5, 6, 7};
        final ArrayHeap heapExtract = new ArrayHeap(valuesExtract);
        System.out.println("Initial heap: " + Arrays.toString(heapExtract.getHeap()));
        for (int i = 1; i <= 7; i++) {
            System.out.println("Extracted value: " + heapExtract.extract());
        }
        System.out.println("Extracted heap length: " + heapExtract.lastIndex);
        System.out.println("Extracted heap: " + Arrays.toString(heapExtract.getHeap()) + "\n");

        final int[] valueChange = {1, 2, 3, 4, 5, 6, 7};
        final ArrayHeap heapChange = new ArrayHeap(valueChange);
        System.out.println("Initial heap: " + Arrays.toString(heapChange.getHeap()));
        System.out.println("Changed to index: " + heapChange.change(4, 9));
        System.out.println("Changed heap: " + Arrays.toString(heapChange.getHeap()));
        System.out.println("Changed to index: " + heapChange.change(4, 1));
        System.out.println("Changed heap: " + Arrays.toString(heapChange.getHeap()) + "\n");

        final int[] valueRemove = {1, 2, 3, 4, 5, 6, 7, 8};
        final ArrayHeap heapRemove = new ArrayHeap(valueRemove);
        for (int index = 7; index >= 0; index--) {
            heapRemove.remove(index);
            System.out.println("Removed heap: " + Arrays.toString(heapRemove.getHeap()));
        }
    }

    @Override
    public int insert(final Integer value) {
        final int newLastIndex = lastIndex + 1;
        if (newLastIndex >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * RESIZE_FACTOR);
        }
        heap[newLastIndex] = value;
        lastIndex = newLastIndex;
        return (heap[(newLastIndex - 1) / 2] > value) ? siftUp(heap, newLastIndex) : newLastIndex;
    }

    @Override
    public void remove(final int index) {
        change(index, Integer.MIN_VALUE);
        extract();
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
            siftDownMax(heap, 0, lastIndex);
        }
        return result;
    }

    @Override
    public int change(final int index, final Integer value) {
        final int result;
        final int current = heap[index];
        heap[index] = value;
        if (current > value) {
            result = siftUp(heap, index);
        } else if (current < value) {
            result = siftDownMax(heap, index, lastIndex);
        } else {
            result = index;
        }
        return result;
    }

    private int[] getHeap() {
        return heap;
    }
}
