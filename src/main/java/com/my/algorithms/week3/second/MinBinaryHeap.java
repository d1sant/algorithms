package com.my.algorithms.week3.second;

import com.my.algorithms.tools.Heaps;

import java.util.Arrays;
import java.util.Collection;

/**
 * Implementation of minimal heap.
 *
 * TODO: iterator, indexOf using binary search
 */
public class MinBinaryHeap<T extends Comparable> extends AbstractBinaryHeap<T> {

    private static final int INITIAL_HEAP_SIZE = 100;
    private static final int RESIZE_FACTOR = 2;

    public MinBinaryHeap() {
        this(INITIAL_HEAP_SIZE);
    }

    public MinBinaryHeap(final int size) {
        this.heap = new Object[size];
        this.lastIndex = -1;
    }

    public MinBinaryHeap(final Collection<T> values) {
        Object[] array = values.toArray();
        this.heap = array;
        this.lastIndex = array.length - 1;
        for (int index = (array.length >>> 1) + 1; index >= 0; index--) {
            siftDownMax(index, lastIndex);
        }
    }

    protected MinBinaryHeap(final T[] heap) {
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

        final Integer[] valuesSiftUp = {1, 2, 3, 5, 8, 2};
        final AbstractBinaryHeap<Integer> heapSiftUp = new MinBinaryHeap<Integer>(valuesSiftUp);
        System.out.println("Initial heap: " + Arrays.toString(valuesSiftUp));
        System.out.println("Sifted up to index: " + Heaps.siftUpMin(valuesSiftUp, 5));
        System.out.println("Sifted up heap: " + Arrays.toString(valuesSiftUp) + "\n");

        System.out.println("Initial heap: " + Arrays.toString(heapSiftUp.getHeap()));
        System.out.println("Inserted index: " + heapSiftUp.insert(2));
        System.out.println("Inserted index: " + heapSiftUp.insert(12));
        System.out.println("Inserted heap: " + Arrays.toString(heapSiftUp.getHeap()) + "\n");

        final AbstractBinaryHeap<Integer> heapInsert = new MinBinaryHeap<Integer>(15);
        for (int i = 1; i < 15; i++) {
            heapInsert.insert(i);
        }
        System.out.println("Inserted heap: " + Arrays.toString(heapInsert.getHeap()) + "\n");

        final int[] valuesSiftDown = {12, 2, 3, 5, 8, 10};
        System.out.println("Initial heap: " + Arrays.toString(valuesSiftDown));
        System.out.println("Sifted down to index: " + Heaps.siftDownMax(valuesSiftDown, 0, valuesSiftDown.length - 1));
        System.out.println("Sifted down heap: " + Arrays.toString(valuesSiftDown) + "\n");

        final Integer[] valuesExtract = {1, 2, 3, 4, 5, 6, 7};
        final MinBinaryHeap<Integer> heapExtract = new MinBinaryHeap<Integer>(valuesExtract);
        System.out.println("Initial heap: " + Arrays.toString(heapExtract.getHeap()));
        for (int i = 1; i <= 7; i++) {
            System.out.println("Extracted value: " + heapExtract.extract());
        }
        System.out.println("Extracted heap length: " + heapExtract.size());
        System.out.println("Extracted heap: " + Arrays.toString(heapExtract.getHeap()) + "\n");

        final Integer[] valueChange = {1, 2, 3, 4, 5, 6, 7};
        final MinBinaryHeap<Integer> heapChange = new MinBinaryHeap<Integer>(valueChange);
        System.out.println("Initial heap: " + Arrays.toString(heapChange.getHeap()));
        System.out.println("Changed to index: " + heapChange.change(4, 9));
        System.out.println("Changed heap: " + Arrays.toString(heapChange.getHeap()));
        System.out.println("Changed to index: " + heapChange.change(9, 1));
        System.out.println("Changed heap: " + Arrays.toString(heapChange.getHeap()) + "\n");

        final Integer[] valueRemove = {1, 2, 3, 4, 5, 6, 7, 8};
        final MinBinaryHeap<Integer> heapRemove = new MinBinaryHeap<Integer>(valueRemove);
        System.out.println("Initial heap: " + Arrays.toString(heapRemove.getHeap()));
        for (int value = 8; value >= 1; value--) {
            heapRemove.remove(value);
            System.out.println("Removed heap: " + Arrays.toString(heapRemove.getHeap()));
        }
        System.out.println("Removed heap length: " + heapRemove.size() + "\n");

        final Collection<Integer> valuesBuild = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
        final MinBinaryHeap<Integer> heapBuild = new MinBinaryHeap<Integer>(valuesBuild);
        System.out.println("Built heap: " + Arrays.toString(heapBuild.getHeap()));
        for (int i = 0; i < 9; i++) {
            System.out.println("Extracted value: " + heapBuild.extract());
        }
    }

    @Override
    public int insert(final T value) {
        final int newLastIndex = lastIndex + 1;
        if (newLastIndex >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * RESIZE_FACTOR);
        }
        heap[newLastIndex] = value;
        lastIndex = newLastIndex;
        return (compare(get((newLastIndex - 1) / 2), value) > 0) ? siftUpMin(newLastIndex) : newLastIndex;
    }

    @Override
    public boolean remove(final T value) {
        final int index = indexOf(value);
        if (index == -1) {
            return false;
        } else {
            siftUp(index, new Priority<T>() {
                @Override
                public boolean compare(T first, T second) {
                    return first != second;
                }
            });
            extract();
            return true;
        }
    }

    @Override
    public T extract() {
        final T result = get();
        heap[0] = heap[lastIndex];
        heap[lastIndex] = null;
        lastIndex--;
        if (compare(get(0), get(1)) > 0 || compare(get(0), get(2)) > 0) {
            siftDownMax(0, lastIndex);
        }
        return result;
    }

    @Override
    public int change(final T value, final T newValue) {
        final int result;
        final int index = indexOf(value);
        if (index == -1) {
            result = -1;
        } else {
            final T current = get(index);
            heap[index] = newValue;
            if (compare(current, newValue) > 0) {
                result = siftUpMin(index);
            } else if (compare(current, newValue) < 0) {
                result = siftDownMax(index, lastIndex);
            } else {
                result = index;
            }
        }
        return result;
    }

    private int indexOf(T value) {
        if (value != null) {
            for (int i = 0; i < heap.length; i++) {
                if (value.equals(heap[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private int compare(final Object x, final Object y) {
        if (x == null && y != null) {
            return -1;
        } else if (x != null && y == null) {
            return 1;
        }
        if (((T) x) != null) {
            return ((T) x).compareTo(y);
        } else {
            return -1;
        }
    }
}
