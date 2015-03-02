package com.my.algorithms.week3.second;

import java.util.Arrays;

import static com.my.algorithms.tools.Arrays.swap;
import static com.my.algorithms.tools.Heaps.siftDownMax;
import static com.my.algorithms.tools.Heaps.siftUpMin;

/**
 * Heap algorithm implementation based on array storage.
 * In order to extend intrinsic array we need create new one with doubled size and copy elements from old array.
 */
public abstract class AbstractArrayHeap<T extends Comparable> implements Heap<T> {

    protected Object[] heap;
    protected int lastIndex;

    public abstract T getMinKey();

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return lastIndex + 1;
    }

    @Override
    public T get() {
        return elementData(0);
    }

    protected T get(final int index) {
        return elementData(index);
    }

    @SuppressWarnings("unchecked")
    protected T[] getHeap() {
        return (T[]) heap;
    }

    @SuppressWarnings("unchecked")
    protected T elementData(int index) {
        return (T) heap[index];
    }

    /**
     * Sifts up the element in the min heap.
     *
     * @param index index of element to sift up
     * @return index of sifted up element
     */
    @SuppressWarnings("unchecked")
    protected int siftUpMin(final int index) {
        return siftUp(index, new Priority<T>() {
            @Override
            public boolean compare(final T first, final T second) {
                return first.compareTo(second) > 0;
            }
        });
    }

    /**
     * Sifts up the element in the heap. Must be provided compare priority in order to build max or min heap.
     *
     * @param index index of element to sift up
     * @param priority compare priority for building different heaps (ex. max, min)
     * @return index of sifted up element
     */
    private int siftUp(final int index, final Priority<T> priority) {
        int parentIndex = (index - 1) / 2;
        int childIndex = index;
        while (parentIndex >= 0 && priority.compare(get(parentIndex), get(childIndex))) {
            swap(heap, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        return childIndex;
    }

    /**
     * Sifts down the element in the min heap.
     *
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @return index of sifted down element
     */
    @SuppressWarnings("unchecked")
    protected int siftDownMax(final int index, final int lastIndex) {
        return siftDown(index, lastIndex, new Priority<T>() {
            @Override
            public boolean compare(T first, T second) {
                return first.compareTo(second) <= 0;
            }
        });
    }

    /**
     * Sifts down the element in the heap. Must be provided compare priority in order to build max or min heap.
     *
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @param priority priority for building different heaps (ex. max, min)
     * @return index of sifted down element
     */
    private int siftDown(final int index, final int lastIndex, final Priority<T> priority) {
        int parentIndex = index;
        while (true) {
            int childIndex = parentIndex * 2 + 1;
            if (childIndex > lastIndex) {
                break;
            }
            if (childIndex + 1 <= lastIndex) {
                childIndex = (priority.compare(get(childIndex), get((childIndex + 1)))) ? childIndex : childIndex + 1;
            }
            if (priority.compare(get(parentIndex), get(childIndex))) {
                break;
            }
            swap(heap, parentIndex, childIndex);
            parentIndex = childIndex;
        }
        return parentIndex;
    }

    private interface Priority<T extends Comparable> {
        boolean compare(final T first, final T second);
    }
}
