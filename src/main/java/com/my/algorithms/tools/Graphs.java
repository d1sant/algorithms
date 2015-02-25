package com.my.algorithms.tools;

import java.util.*;

/**
 * Auxiliary API for working graphs.
 */
public class Graphs {

    public static String toString(final int[][] graph) {
        final StringBuilder result = new StringBuilder();
        for (int[] edges : graph) {
            result.append("[").append(java.util.Arrays.toString(edges)).append("]");
        }
        return result.toString();
    }

    public static void addEdge(final int[][] graph, final int firstVertex, final int secondVertex) {
        final int vertex;
        final int vertexForAdd;
        if (firstVertex < secondVertex) {
            vertex = firstVertex;
            vertexForAdd = secondVertex;
        } else {
            vertex = secondVertex;
            vertexForAdd = firstVertex;
        }
        if (graph[vertex] != null) {
            int length = graph[vertex].length;
            for (int edgeIndex = 0; edgeIndex < length; edgeIndex++) {
                if (graph[vertex][edgeIndex] == vertexForAdd) {
                    return;
                }
            }
            final int[] edges = new int[length + 1];
            System.arraycopy(graph[vertex], 0, edges, 0, length);
            edges[length] = vertexForAdd;
            graph[vertex] = edges;
        } else {
            graph[vertex] = new int[]{vertexForAdd};
        }
    }
}
