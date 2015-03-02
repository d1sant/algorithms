package com.my.algorithms.tools;

import static com.my.algorithms.tools.Arrays.swap;

/**
 * Auxiliary API for working with Heaps.
 */
public class Heaps {

    /**
     * Sifts up the element in the max heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift up
     * @return index of sifted up element
     */
    public static int siftUpMax(final int[] heap, final int index) {
        return siftUp(heap, index, new Priority<Integer>() {
            @Override
            public boolean compare(final Integer first, final Integer second) {
                return first < second;
            }
        });
    }

    /**
     * Sifts up the element in the max heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift up
     * @return index of sifted up element
     */
    public static <T extends Comparable<T>> int siftUpMax(final T[] heap, final int index) {
        return siftUp(heap, index, new Priority<T>() {
            @Override
            public boolean compare(T first, T second) {
                return first.compareTo(second) < 0;
            }
        });
    }

    /**
     * Sifts up the element in the min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift up
     * @return index of sifted up element
     */
    public static int siftUpMin(final int[] heap, final int index) {
        return siftUp(heap, index, new Priority<Integer>() {
            @Override
            public boolean compare(final Integer first, final Integer second) {
                return first > second;
            }
        });
    }

    /**
     * Sifts up the element in the min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift up
     * @return index of sifted up element
     */
    public static <T extends Comparable<T>> int siftUpMin(final T[] heap, final int index) {
        return siftUp(heap, index, new Priority<T>() {
            @Override
            public boolean compare(final T first, final T second) {
                return first.compareTo(second) > 0;
            }
        });
    }


    /**
     * Sifts up the element in the heap. Must be provided compare operator in order to build max or min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift up
     * @param operator compare operator for building different heaps (ex. max, min)
     * @return index of sifted up element
     */
    private static int siftUp(final int[] heap, final int index, final Priority<Integer> operator) {
        int parentIndex = (index - 1) / 2;
        int childIndex = index;
        while (parentIndex >= 0 && operator.compare(heap[parentIndex], heap[childIndex])) {
            swap(heap, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        return childIndex;
    }

    /**
     * Sifts up the element in the heap. Must be provided compare priority in order to build max or min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift up
     * @param priority compare priority for building different heaps (ex. max, min)
     * @return index of sifted up element
     */
    private static <T extends Comparable> int siftUp(final T[] heap, final int index, final Priority<T> priority) {
        int parentIndex = (index - 1) / 2;
        int childIndex = index;
        while (parentIndex >= 0 && priority.compare(heap[parentIndex], heap[childIndex])) {
            swap(heap, parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        return childIndex;
    }

    /**
     * Sifts down the element in the min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @return index of sifted down element
     */
    public static int siftDownMax(final int[] heap, final int index, final int lastIndex) {
        return siftDown(heap, index, lastIndex, new Priority<Integer>() {
            @Override
            public boolean compare(Integer first, Integer second) {
                return first <= second;
            }
        });
    }

    /**
     * Sifts down the element in the min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @return index of sifted down element
     */
    public static <T extends Comparable<T>> int siftDownMax(final T[] heap, final int index, final int lastIndex) {
        return siftDown(heap, index, lastIndex, new Priority<T>() {
            @Override
            public boolean compare(T first, T second) {
                return first.compareTo(second) <= 0;
            }
        });
    }

    /**
     * Sifts down the element in the max heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @return index of sifted down element
     */
    public static int siftDownMin(final int[] heap, final int index, final int lastIndex) {
        return siftDown(heap, index, lastIndex, new Priority<Integer>() {
            @Override
            public boolean compare(Integer first, Integer second) {
                return first >= second;
            }
        });
    }

    /**
     * Sifts down the element in the max heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @return index of sifted down element
     */
    public static <T extends Comparable<T>> int siftDownMin(final T[] heap, final int index, final int lastIndex) {
        return siftDown(heap, index, lastIndex, new Priority<T>() {
            @Override
            public boolean compare(T first, T second) {
                return first.compareTo(second) >= 0;
            }
        });
    }

    /**
     * Sifts down the element in the heap. Must be provided compare priority in order to build max or min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @param priority priority for building different heaps (ex. max, min)
     * @return index of sifted down element
     */
    private static int siftDown(final int[] heap, final int index, final int lastIndex, final Priority<Integer> priority) {
        int parentIndex = index;
        while (true) {
            int childIndex = parentIndex * 2 + 1;
            if (childIndex > lastIndex) {
                break;
            }
            if (childIndex + 1 <= lastIndex) {
                childIndex = (priority.compare(heap[childIndex], heap[(childIndex + 1)])) ? childIndex : childIndex + 1;
            }
            if (priority.compare(heap[parentIndex], heap[childIndex])) {
                break;
            }
            swap(heap, parentIndex, childIndex);
            parentIndex = childIndex;
        }
        return parentIndex;
    }

    /**
     * Sifts down the element in the heap. Must be provided compare priority in order to build max or min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @param priority priority for building different heaps (ex. max, min)
     * @return index of sifted down element
     */
    private static <T extends Comparable<T>> int siftDown(final T[] heap, final int index, final int lastIndex, final Priority<T> priority) {
        int parentIndex = index;
        while (true) {
            int childIndex = parentIndex * 2 + 1;
            if (childIndex > lastIndex) {
                break;
            }
            if (childIndex + 1 <= lastIndex) {
                childIndex = (priority.compare(heap[childIndex], heap[(childIndex + 1)])) ? childIndex : childIndex + 1;
            }
            if (priority.compare(heap[parentIndex], heap[childIndex])) {
                break;
            }
            swap(heap, parentIndex, childIndex);
            parentIndex = childIndex;
        }
        return parentIndex;
    }

    public interface Priority<T extends Comparable> {
        boolean compare(final T first, final T second);
    }
}
