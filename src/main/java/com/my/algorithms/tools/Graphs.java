package com.my.algorithms.tools;

/**
 * Auxiliary API for working graphs.
 */
public class Graphs {

    public static String toString(final int[][] graph) {
        final StringBuilder result = new StringBuilder();
        for (int[] edges : graph) {
            result.append("{").append(java.util.Arrays.toString(edges)).append("}");
        }
        return result.toString();
    }

    /**
     * Adds directed edge to graph.
     * @param graph graph
     * @param firstVertex index of first vertex. Starts with 1
     * @param secondVertex index of second vertex. Starts with 1
     */
    public static void addUndirected(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
        addEdge(graph, secondVertex - 1, firstVertex - 1);
    }

    /**
     * Adds undirected edge to graph
     * @param graph array presented graph
     * @param firstVertex index of first vertex. Starts with 1
     * @param secondVertex index of second vertex. Starts with 1
     */
    public static void addDirected(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
    }

    protected static void addEdge(final int[][] graph, final int vertexFrom, final int vertexTo) {
        if (graph[vertexFrom] != null) {
            int length = graph[vertexFrom].length;
            for (int edgeIndex = 0; edgeIndex < length; edgeIndex++) {
                if (graph[vertexFrom][edgeIndex] == vertexTo) {
                    return;
                }
            }
            final int[] edges = new int[length + 1];
            System.arraycopy(graph[vertexFrom], 0, edges, 0, length);
            edges[length] = vertexTo;
            graph[vertexFrom] = edges;
        } else {
            graph[vertexFrom] = new int[]{vertexTo};
        }
    }
}
