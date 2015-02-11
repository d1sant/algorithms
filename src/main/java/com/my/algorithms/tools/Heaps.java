package com.my.algorithms.tools;

import static com.my.algorithms.tools.Arrays.swap;

public class Heaps {

    /**
     * Sifts up the element in the heap.
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
    public static int siftDown(final int[] heap, final int index, final int lastIndex) {
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
            swap(heap, parentIndex, childIndex);
            parentIndex = childIndex;
        }
        return parentIndex;
    }

    /**
     * Sifts down the element in the min heap.
     *
     * @param heap array of elements that presents a heap
     * @param index index of element to sift down
     * @param lastIndex last element of a heap
     * @return index of sifted down element
     *
     * TODO refactor it to use function of comparison
     */
    public static int siftDownMax(final int[] heap, final int index, final int lastIndex) {
        int parentIndex = index;
        while (true) {
            int childIndex = parentIndex * 2 + 1;
            if (childIndex > lastIndex) {
                break;
            }
            if (childIndex + 1 <= lastIndex) {
                childIndex = (heap[childIndex] >= heap[(childIndex + 1)]) ? childIndex : childIndex + 1;
            }
            if (heap[parentIndex] >= heap[childIndex]) {
                break;
            }
            swap(heap, parentIndex, childIndex);
            parentIndex = childIndex;
        }
        return parentIndex;
    }
}
