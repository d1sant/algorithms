package com.my.algorithms.week3.second;

/**
 * Heap algorithm implementation based on OOP approach.
 */
public class ObjectHeap implements Heap<Integer> {

    public static void main(final String[] args) {
        final ObjectHeap heap = new ObjectHeap();
    }

    @Override
    public boolean isEmpty() {
        return false; // TODO Implement me
    }

    @Override
    public int size() {
        return 0; // TODO implement me
    }

    /**
     * Insert value to the heap.
     * @param value value of element
     * @return index of inserted value (might be sifted up)
     */
    public int insert(final Integer value) {
       return -1; // TODO implement me
    }

    @Override
    public boolean remove(final Integer value) {
        return false; // TODO implement me
    }

    @Override
    public Integer get() {
        return null;
    }

    /**
     * Extracts element with max priority.
     * @return value of element;
     */
    public Integer extract() {
        return -1; // TODO implement me
    }

    @Override
    public int change(final Integer value, final Integer newValue) {
        return -1; // TODO implement me
    }

    private void siftUp(final Node node) {
        // TODO implement me
    }

    private void siftDown(final Node node) {
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
