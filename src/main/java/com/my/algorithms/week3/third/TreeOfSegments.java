package com.my.algorithms.week3.third;

import java.util.Arrays;

public class TreeOfSegments {

    private final int[] segmentTree;
    private final int length;

    public static void main(String[] args) {

        System.out.println("3: " + round(3));
        System.out.println("6: " + round(6));
        System.out.println("19: " + round(19));
        System.out.println("79: " + round(79));
        System.out.println("3445: " + round(3445));
        System.out.println("59994949: " + round(59994949) + "\n");

        System.out.println(1 << 17);
        System.out.println(2 << 3);
        System.out.println(1 << 3);
        System.out.println(3 << 1);

        final int numberOfVal = 5;
        final TreeOfSegments treeOfSegments = new TreeOfSegments(numberOfVal);
        final int[] values = {1, 2, 3, 4, 5};
        final int numberOfValRounded = round(numberOfVal);
        for (int i = 0; i < values.length; i++) {
            treeOfSegments.set(1, 0, numberOfValRounded - 1, i, values[i]);
        }

        System.out.println("Set array: " + Arrays.toString(treeOfSegments.segmentTree));

        System.out.println("Min(1,5): " + treeOfSegments.getMin(1, 0, numberOfValRounded - 1, 0, 4));
        treeOfSegments.set(1, 0, numberOfValRounded - 1, 0, 8); // 1, 8
        System.out.println("Min(1,5): " + treeOfSegments.getMin(1, 0, numberOfValRounded - 1, 0, 4));
        System.out.println("Min(1,1): " + treeOfSegments.getMin(1, 0, numberOfValRounded - 1, 0, 0));
        System.out.println("Min(1,3): " + treeOfSegments.getMin(1, 0, numberOfValRounded - 1, 0, 2));
        treeOfSegments.set(1, 0, numberOfValRounded - 1, 2, 1); // 3, 1
        System.out.println("Min(2,4): " + treeOfSegments.getMin(1, 0, numberOfValRounded - 1, 1, 3));
    }

    public TreeOfSegments(final int elementNum) {
        this.length = 2 * round(elementNum);
        this.segmentTree = new int[length];
        Arrays.fill(segmentTree, round(elementNum), length, Integer.MAX_VALUE);
        System.out.println("Length: " + length);
        System.out.println("Array: " + Arrays.toString(segmentTree));
    }

    public static int round(final int value) {
        return 1 << (value == 0 ? 0 : 32 - Integer.numberOfLeadingZeros(value - 1));
    }

    /**
     * Sets value segment tree with calculation of minimum.
     *
     * @param vertex vertex
     * @param vertexLeft left border of segment that corresponds to vertex vertex
     * @param vertexRight right border of segment that corresponds to vertex vertex
     * @param index index of cell
     * @param value value that will be placed in cell with index index
     */
    public void set(final int vertex, final int vertexLeft, final int vertexRight, final int index, final int value) {
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
     * @param vertex vertex
     * @param vertexLeft left border of segment that corresponds to vertex vertex
     * @param vertexRight right border of segment that corresponds to vertex vertex
     * @param left left border
     * @param right right border
     * @return minimum on defined segment
     */
    public int getMin(final int vertex, final int vertexLeft, final int vertexRight, final int left, final int right) {
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
