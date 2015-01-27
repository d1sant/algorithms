package com.my.algorithms.week3.first;

/**
 * Heap algorithm implementation based on OOP approach.
 */
public class ObjectHeap implements Heap<Integer> {

    public static void main(final String[] args) {
        final ObjectHeap heap = new ObjectHeap();
    }

    /**
     * Extracts element with max priority.
     * @return value of element;
     */
    public Integer extract() {
        return -1; // TODO implement me
    }

    /**
     * Insert value to the heap.
     * @param value value of element
     */
    public void insert(final Integer value) {
        // TODO implement me
    }

    private void siftUp(Node node) {
        // TODO implement me
    }

    private void siftDown(Node node) {
        // TODO implement me
    }

    private static class Node {
        private Node root;
        private Node left;
        private Node right;

        private Node() {}

        private Node(Node root) {
            this.root = root;
        }

        private Node(Node root, Node left, Node right) {
            this.root = root;
            this.left = left;
            this.right = right;
        }
    }

}
