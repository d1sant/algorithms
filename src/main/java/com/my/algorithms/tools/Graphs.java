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
     * Adds undirected edge to graph.
     * @param graph graph
     * @param firstVertex index of first vertex. Starts with 1
     * @param secondVertex index of second vertex. Starts with 1
     */
    public static void addUndirected(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
        addEdge(graph, secondVertex - 1, firstVertex - 1);
    }

    /**
     * Adds undirected normalized edge to graph.
     * @param graph graph
     * @param firstVertex index of first vertex. Starts with 0
     * @param secondVertex index of second vertex. Starts with 0
     */
    public static void addUndirectedNormalized(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex, secondVertex);
        addEdge(graph, secondVertex, firstVertex);
    }

    /**
     * Adds directed edge to graph
     * @param graph array presented graph
     * @param firstVertex index of first vertex. Starts with 1
     * @param secondVertex index of second vertex. Starts with 1
     */
    public static void addDirected(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
    }

    /**
     * Adds directed normalized edge to graph
     * @param graph array presented graph
     * @param firstVertex index of first vertex. Starts with 0
     * @param secondVertex index of second vertex. Starts with 0
     */
    public static void addDirectedNormalized(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex, secondVertex);
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

    /**
     * Returns reverted graph.
     * @param graph array presented graph
     * @return reverted graph
     */
    public static int[][] reverse(final int[][] graph) {
        final int[][] reversed = new int[graph.length][];
        for (int vertexFrom = 0; vertexFrom < graph.length; vertexFrom++) {
            if (graph[vertexFrom] != null) {
                for (final int vertexTo : graph[vertexFrom]) {
                    addDirectedNormalized(reversed, vertexTo, vertexFrom);
                }
            }
        }
        return reversed;
    }
}
