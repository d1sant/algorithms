package com.my.algorithms.week6.third;

import java.util.Arrays;
import java.util.Scanner;

public class BellmanFordTask1 {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int vertexes = scanner.nextInt();
        final int edges = scanner.nextInt();
        final int[][][] graph = new int[vertexes][][];
        scanner.nextLine();
        for (int index = 0; index < edges; index++) {
            addDirected(graph, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
        final Triple<int[], int[], Boolean> result = bellmanFord(graph, 0);
        System.out.println(result.third ? 1 : 0);
    }

    private static void addDirected(final int[][][] graph, final int vertex, final int vertexTo, final int weight) {
        addEdge(graph, vertex - 1, vertexTo - 1, weight);
    }

    private static void addEdge(final int[][][] graph, final int vFrom, final int vTo, final int weight) {
        if (graph[vFrom] != null) {
            int length = graph[vFrom].length;
            for (int edgeIndex = 0; edgeIndex < length; edgeIndex++) {
                if (graph[vFrom][edgeIndex][0] == vTo && graph[vFrom][edgeIndex][1] == weight) {
                    return;
                }
            }
            final int[][] edges = new int[length + 1][];
            System.arraycopy(graph[vFrom], 0, edges, 0, length);
            edges[length] = new int[]{vTo, weight};
            graph[vFrom] = edges;
        } else {
            graph[vFrom] = new int[][]{{vTo, weight}};
        }
    }

    public static Triple<int[], int[], Boolean> bellmanFord(final int[][][] graph, final int vertex) {
        final int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        final int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);
        distances[vertex] = 0;
        boolean isNegativeCycle = false;
        for (int i = 0; i < graph.length + 1; i++) {
            boolean isRelaxed = false;
            for (int vertexFrom = 0; vertexFrom < graph.length; vertexFrom++) {
                final int[][] edges = graph[vertexFrom];
                if (edges != null) {
                    for (int[] edge : edges) {
                        final int vertexTo = edge[0];
                        final int weight = edge[1];
                        final long relaxedDistanceTo = (long) distances[vertexFrom] + weight;
                        if (distances[vertexTo] > relaxedDistanceTo) {
                            distances[vertexTo] = (int) relaxedDistanceTo;
                            previous[vertexTo] = vertexFrom;
                            isRelaxed = true;
                        }
                    }
                }
            }
            if (!isRelaxed) {
                break;
            }
            if (i == graph.length) {
                isNegativeCycle = true;
                break;
            }
        }
        return new Triple<int[], int[], Boolean>(distances, previous, isNegativeCycle);
    }

    private static class Triple<F, S, T> {

        public final F first;
        public final S second;
        public final T third;

        public Triple(F first, S second, T third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {
            return "{i=" + first + ", j=" + second + ", k=" + third + "}";
        }
    }
}
