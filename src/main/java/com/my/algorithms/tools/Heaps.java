package com.my.algorithms.tools;

import static com.my.algorithms.tools.Arrays.swap;

public class Heaps {

    /**
     * Sifts up the element in the min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift up
     * @return index of sifted up element
     */
    public static int siftUp(final int[] heap, final int index) {
        int parentIndex = (index - 1) / 2;
        int childIndex = index;
        while (parentIndex >= 0 && heap[parentIndex] > heap[childIndex]) {
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
        return siftDown(heap, index, lastIndex, new Comparable() {
            @Override
            public boolean compare(int first, int second) {
                return first <= second;
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
        return siftDown(heap, index, lastIndex, new Comparable() {
            @Override
            public boolean compare(int first, int second) {
                return first >= second;
            }
        });
    }

    /**
     * Sifts down the element in the heap. Must be provided compare operator in order to build max or min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @param operator compare operator for building different heaps (ex. max, min)
     * @return index of sifted down element
     */
    private static int siftDown(final int[] heap, final int index, final int lastIndex, final Comparable operator) {
        int parentIndex = index;
        while (true) {
            int childIndex = parentIndex * 2 + 1;
            if (childIndex > lastIndex) {
                break;
            }
            if (childIndex + 1 <= lastIndex) {
                childIndex = (operator.compare(heap[childIndex], heap[(childIndex + 1)])) ? childIndex : childIndex + 1;
            }
            if (operator.compare(heap[parentIndex], heap[childIndex])) {
                break;
            }
            swap(heap, parentIndex, childIndex);
            parentIndex = childIndex;
        }
        return parentIndex;
    }

    public interface Comparable {
        boolean compare(final int first, final int second);
    }
}
