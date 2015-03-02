package com.my.algorithms.week3.second;

import java.util.Arrays;
import java.util.Scanner;

public class MaxBinaryHeapTask1 {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int operations = scanner.nextInt();
        scanner.nextLine();
        final ArrayHeap heap = new ArrayHeap(operations);
        while(operations > 0) {
            final String operation = scanner.nextLine();
            if (operation.startsWith("Insert")) {
                final Integer value = -1 * Integer.parseInt(operation.substring(7));
                heap.insert(value);
            } else if (operation.startsWith("Extract")) {
                System.out.println(-1 * heap.extract());
            } else {
                System.out.println("Unknown command");
            }
            operations--;
        }
        scanner.close();
    }

    public static class ArrayHeap {

        private static final int RESIZE_FACTOR = 2;

        private int[] heap;
        private int lastIndex;

        public ArrayHeap(final int size) {
            this.heap = new int[size];
            this.lastIndex = -1;
        }

        public int insert(final Integer value) {
            final int newLastIndex = lastIndex + 1;
            if (newLastIndex >= heap.length) {
                heap = Arrays.copyOf(heap, heap.length * RESIZE_FACTOR);
            }
            heap[newLastIndex] = value;
            lastIndex = newLastIndex;
            return (heap[(newLastIndex - 1) / 2] > value) ? siftUp(newLastIndex) : newLastIndex;
        }

        public void remove(final int index) {
            change(index, Integer.MIN_VALUE);
            extract();
        }

        public Integer get() {
            return heap[0];
        }

        public Integer extract() {
            final Integer result = heap[0];
            heap[0] = heap[lastIndex];
            heap[lastIndex] = 0;
            lastIndex--;
            if (heap[0] > heap[1] || heap[0] > heap[2]) {
                siftDown(0);
            }
            return result;
        }

        public int change(final int index, final Integer value) {
            final int result;
            final int current = heap[index];
            heap[index] = value;
            if (current > value) {
                result = siftUp(index);
            } else if (current < value) {
                result = siftDown(index);
            } else {
                result = index;
            }
            return result;
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
    }
}
