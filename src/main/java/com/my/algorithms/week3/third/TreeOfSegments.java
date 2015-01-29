package com.my.algorithms.week3.third;

public class TreeOfSegments {

    private final int[] segmentTree;
    private final int length;

    public static void main(String[] args) {

        System.out.println(round(3));
        System.out.println(round(19));
        System.out.println(round(79));
        System.out.println(round(3445));
        System.out.println(round(59994949) + "\n");

        System.out.println(1 << 17);
        System.out.println(2 << 3);
        System.out.println(1 << 3);
        System.out.println(3 << 1);
    }

    public TreeOfSegments(final int elementNum) {
        this.length = 2 * round(elementNum);
        this.segmentTree = new int[length];
    }

    public static int round(final int value) {
        return 2 << (value == 0 ? 0 : 32 - Integer.numberOfLeadingZeros(value - 1));
    }

    /**
     * Sets value segment tree with calculation of minimum.
     *
     * @param v vertex
     * @param vl left border of segment that corresponds to vertex v
     * @param vr right border of segment that corresponds to vertex v
     * @param i index of cell
     * @param x value that will be placed in cell with index i
     */
    public void setX(final int v, final int vl, final int vr, final int i, final int x) {
        if (i < vl || vr < i) {
            return;
        }
        if (vl == vr) {
            segmentTree[v] = x;
            return;
        }
        int vm = (vl + vr) / 2;
        setX(2 * v, vl, vm, i, x);
        setX(2 * v + 1, vm + 1, vr, i, x);
        segmentTree[v] = Math.min(segmentTree[v * 2], segmentTree[v * 2 + 1]);
    }

    /**
     * Gets minimum on defined segment
     *
     * @param v vertex
     * @param vl left border of segment that corresponds to vertex v
     * @param vr right border of segment that corresponds to vertex v
     * @param l left border
     * @param r right border
     * @return minimum of defined segment
     */
    public int getMin(final int v, final int vl, final int vr, final int l, final int r) {
        if (r < vl || vr < l) {
            return Integer.MAX_VALUE;
        }
        if (l <= vl && vr <= r) {
            return segmentTree[v];
        }
        final int vm = (vl + vr) / 2;
        final int a = getMin(2 * v, vl, vm, l, r);
        final int b = getMin(2 * v + 1, vm + 1, vr, l, r);
        return Math.min(a, b);
    }
}
