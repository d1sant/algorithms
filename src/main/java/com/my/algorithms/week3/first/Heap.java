package com.my.algorithms.week3.first;

public class Heap {

    public static void main(final String[] args) {
        final Heap heap = new Heap();
    }

    /**
     * Extracts element with max priority.
     * @return value of element;
     */
    public int extract() {
        return -1; // TODO implement me
    }

    /**
     * Insert value to the heap.
     * @param value value of element
     */
    public void insert(final int value) {
        // TODO implement me
    }

    private void siftUp(Node node) {
        // TODO implement me
    }

    private void siftDown(Node node) {
        // TODO implement me
    }

    private static class Node {
        private Node parent;
        private Node leftLeaf;
        private Node rightLeaf;

        private Node(Node parent) {
            this.parent = parent;
        }

        private Node(Node parent, Node leftLeaf, Node rightLeaf) {
            this.parent = parent;
            this.leftLeaf = leftLeaf;
            this.rightLeaf = rightLeaf;
        }
    }

}
