package com.my.algorithms.week3.third;

import java.util.Arrays;

/**
 * Tree of segment algorithm implementation.
 */
public class TreeOfSegments {

    private final int[] segmentTree;
    private final int numberOfElements; // rounded value to nearest power of two

    public static void main(final String[] args) {

        // rounding to nearest power of two
        System.out.println("3: " + round(3));
        System.out.println("6: " + round(6));
        System.out.println("8: " + round(8));
        System.out.println("19: " + round(19));
        System.out.println("79: " + round(79));
        System.out.println("3445: " + round(3445));
        System.out.println("59994949: " + round(59994949) + "\n");

        // power of two with left shift
        System.out.println(1 << 17);
        System.out.println(2 << 3);
        System.out.println(1 << 3);
        System.out.println((3 << 1) + "\n");

        final TreeOfSegments treeOfSegments = new TreeOfSegments(5);
        final int[] values = {1, 2, 3, 4, 5};
        System.out.println("Values: " + Arrays.toString(values));
        for (int index = 0; index < values.length; index++) {
            treeOfSegments.set(index + 1, values[index]);
        }

        System.out.println("Set array: " + Arrays.toString(treeOfSegments.segmentTree));

        System.out.println("Min(1,5): " + treeOfSegments.getMin(1, 5));
        treeOfSegments.set(1, 8);
        System.out.println("Min(1,5): " + treeOfSegments.getMin(1, 5));
        System.out.println("Min(1,1): " + treeOfSegments.getMin(1, 1));
        System.out.println("Min(1,3): " + treeOfSegments.getMin(1, 3));
        treeOfSegments.set(3, 1);
        System.out.println("Min(2,4): " + treeOfSegments.getMin(2, 4) + "\n");

        final TreeOfSegments fullTreeOfSegments = new TreeOfSegments(5);
        final int[] fullValues = {5, 2, 7, 4, 4, 6, 8, 10};
        System.out.println("Values: " + Arrays.toString(fullValues));
        for (int index = 0; index < fullValues.length; index++) {
            fullTreeOfSegments.set(index + 1, fullValues[index]);
        }

        System.out.println("Set array: " + Arrays.toString(fullTreeOfSegments.segmentTree));
    }

    public TreeOfSegments(final int elementNum) {
        numberOfElements = round(elementNum);
        final int length = 2 * numberOfElements;
        segmentTree = new int[length];
        Arrays.fill(segmentTree, numberOfElements, length, Integer.MAX_VALUE);
    }

    public static int round(final int value) {
        return 1 << (value == 0 ? 0 : 32 - Integer.numberOfLeadingZeros(value - 1));
    }

    /**
     * Sets value to segment tree with calculation of minimum.
     *
     * @param index index of element. Indexes begin with 1
     * @param value value of element
     */
    public void set(final int index, final int value) {
        set(1, 0, numberOfElements - 1, index - 1, value);
    }

    /**
     * Sets value to segment tree with calculation of minimum.
     *
     * @param vertex vertex
     * @param vertexLeft left index of sub array that corresponds to vertexes
     * @param vertexRight right index of sub array that corresponds to vertexes
     * @param index index of cell. Indexes begin with 0
     * @param value value that will be placed in cell with index index
     */
    protected void set(final int vertex, final int vertexLeft, final int vertexRight, final int index, final int value) {
        if (index < vertexLeft || vertexRight < index) {
            return;
        }
        if (vertexLeft == vertexRight) {
            segmentTree[vertex] = value;
            return;
        }
        int vertexMiddle = (vertexLeft + vertexRight) / 2;
        set(2 * vertex, vertexLeft, vertexMiddle, index, value);
        set(2 * vertex + 1, vertexMiddle + 1, vertexRight, index, value);
        segmentTree[vertex] = Math.min(segmentTree[vertex * 2], segmentTree[vertex * 2 + 1]);
    }

    /**
     * Gets minimum on defined segment.
     *
     * @param left left index. Indexes begin with 1
     * @param right right index. Indexes begin with 1
     * @return minimum on defined segment
     */
    public int getMin(final int left, final int right) {
        return getMin(1, 0, numberOfElements - 1, left - 1, right - 1);
    }

    /**
     * Gets minimum on defined segment.
     *
     * @param vertex vertex
     * @param vertexLeft left index of sub array that corresponds to vertexes
     * @param vertexRight right index of sub array that corresponds to vertexes
     * @param left left index. Indexes begin with 0
     * @param right right index.  Indexes begin with 0
     * @return minimum on defined segment
     */
    protected int getMin(final int vertex, final int vertexLeft, final int vertexRight, final int left, final int right) {
        if (right < vertexLeft || vertexRight < left) {
            return Integer.MAX_VALUE;
        }
        if (left <= vertexLeft && vertexRight <= right) {
            return segmentTree[vertex];
        }
        final int vertexMiddle = (vertexLeft + vertexRight) / 2;
        final int minLeft = getMin(2 * vertex, vertexLeft, vertexMiddle, left, right);
        final int minRight = getMin(2 * vertex + 1, vertexMiddle + 1, vertexRight, left, right);
        return Math.min(minLeft, minRight);
    }
}
