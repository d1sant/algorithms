package com.my.algorithms.week6.third;

import com.my.algorithms.domain.dto.Triple;
import com.my.algorithms.tools.Graphs;

import java.util.Arrays;

import static com.my.algorithms.tools.Graphs.addDirected;

/**
 * Bellman-Ford algorithm for searching in graph with negative weights.
 * Implemented check on negative cycle.
 */
public class BellmanFord {

    public static final long MAX_VALUE_LONG = 1000000000000L;
    public static final int MAX_VALUE_INT = Integer.MAX_VALUE;

    public static void main(final String[] args) {

        final int[][][] graph = new int[5][][];
        addDirected(graph, 1, 2, 4);
        addDirected(graph, 1, 3, 3);
        addDirected(graph, 2, 4, 4);
        addDirected(graph, 2, 3, -2);
        addDirected(graph, 3, 4, -3);
        addDirected(graph, 3, 5, 1);
        addDirected(graph, 4, 5, 2);
        System.out.println("Weighted Graph: " + Graphs.toString(graph));
        final Triple<int[], int[], Boolean> result = bellmanFord(graph, 0);
        System.out.println("Distances: " + Arrays.toString(result.first));
        System.out.println("Previous: " + Arrays.toString(result.second));
        System.out.println("Distance from 1 to 5 is equaled " + result.first[4]);
        System.out.println("Is there a negative cycle: " + result.third + "\n");

        final int[][][] nGraph = new int[3][][];
        addDirected(nGraph, 1, 2, 2);
        addDirected(nGraph, 2, 3, -4);
        addDirected(nGraph, 3, 1, 1);
        System.out.println("Weighted Graph: " + Graphs.toString(nGraph));
        final Triple<int[], int[], Boolean> result2 = bellmanFord(nGraph, 0);
        System.out.println("Distances: " + Arrays.toString(result2.first));
        System.out.println("Previous: " + Arrays.toString(result2.second));
        System.out.println("Is there a negative cycle: " + result2.third);
    }

    public static Triple<int[], int[], Boolean> bellmanFord(final int[][][] graph, final int vertex) {
        final int[] distances = new int[graph.length];
        Arrays.fill(distances, MAX_VALUE_INT);
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

    public static Triple<long[], int[], Boolean> bellmanFord(final long[][][] graph, final int vertex) {
        final long[] distances = new long[graph.length];
        Arrays.fill(distances, MAX_VALUE_LONG);
        final int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);
        distances[vertex] = 0;
        boolean isNegativeCycle = false;
        for (int i = 0; i < graph.length + 1; i++) {
            boolean isRelaxed = false;
            for (int vertexFrom = 0; vertexFrom < graph.length; vertexFrom++) {
                final long[][] edges = graph[vertexFrom];
                if (edges != null) {
                    for (long[] edge : edges) {
                        final int vertexTo = (int) edge[0];
                        final long weight = edge[1];
                        final long relaxedDistanceTo = distances[vertexFrom] + weight;
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
        return new Triple<long[], int[], Boolean>(distances, previous, isNegativeCycle);
    }
}
