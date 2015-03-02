package com.my.algorithms.tools;

/**
 * Auxiliary API for working graphs.
 */
public class Graphs {

    /**
     * Presents unweighted graph as string.
     *
     * @param graph array presented graph
     * @return string presented graph
     */
    public static String toString(final int[][] graph) {
        final StringBuilder result = new StringBuilder();
        for (final int[] edges : graph) {
            result.append('{');
            if (edges != null) {
                for (final int edge : edges) {
                    result.append(edge).append(", ");
                }
                result.delete(result.length() - 2, result.length());
            } else {
                result.append("null");
            }
            result.append('}');
        }
        return result.toString();
    }

    /**
     * Presents weighted graph as string.
     *
     * @param graph array presented graph
     * @return string presented graph
     */
    public static String toString(final int[][][] graph) {
        final StringBuilder result = new StringBuilder();
        for (int[][] edges : graph) {
            result.append('{');
            if (edges != null) {
                for (final int[] edge : edges) {
                    result.append(edge[0]).append(':').append(edge[1]).append(", ");
                }
                result.delete(result.length() - 2, result.length());
            } else {
                result.append("null");
            }
            result.append('}');
        }
        return result.toString();
    }

    /**
     * Adds undirected edge to unweighted graph.
     *
     * @param graph        graph
     * @param firstVertex  index of source vertex. Starts with 1
     * @param secondVertex index of target vertex. Starts with 1
     */
    public static void addUndirected(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
        addEdge(graph, secondVertex - 1, firstVertex - 1);
    }

    /**
     * Adds undirected edge to weighted graph.
     *
     * @param graph    graph
     * @param vertex   index of source vertex. Starts with 1
     * @param vertexTo index of target vertex. Starts with 1
     */
    public static void addUndirected(final int[][][] graph, final int vertex, final int vertexTo, final int weight) {
        addEdge(graph, vertex - 1, vertexTo - 1, weight);
        addEdge(graph, vertexTo - 1, vertex - 1, weight);
    }

    /**
     * Adds undirected normalized edge to unweighted graph.
     *
     * @param graph    graph
     * @param vertex   index of source vertex. Starts with 0
     * @param vertexTo index of target vertex. Starts with 0
     */
    public static void addUndirectedNormalized(final int[][] graph, final int vertex, final int vertexTo) {
        addEdge(graph, vertex, vertexTo);
        addEdge(graph, vertexTo, vertex);
    }

    /**
     * Adds undirected normalized edge to weighted graph.
     *
     * @param graph    graph
     * @param vertex   index of source vertex. Starts with 0
     * @param vertexTo index of target vertex. Starts with 0
     */
    public static void addUndirectedNormalized(final int[][][] graph, final int vertex, final int vertexTo, final int weight) {
        addEdge(graph, vertex, vertexTo, weight);
        addEdge(graph, vertexTo, vertex, weight);
    }

    /**
     * Adds directed edge to unweighted graph.
     *
     * @param graph    array presented graph
     * @param vertex   index of source vertex. Starts with 1
     * @param vertexTo index of target vertex. Starts with 1
     */
    public static void addDirected(final int[][] graph, final int vertex, final int vertexTo) {
        addEdge(graph, vertex - 1, vertexTo - 1);
    }

    /**
     * Adds directed edge to weighted graph.
     *
     * @param graph    array presented graph
     * @param vertex   index of source vertex. Starts with 1
     * @param vertexTo index of target vertex. Starts with 1
     * @param weight   weight of edge
     */
    public static void addDirected(final int[][][] graph, final int vertex, final int vertexTo, final int weight) {
        addEdge(graph, vertex - 1, vertexTo - 1, weight);
    }

    /**
     * Adds directed normalized edge to unweighted graph.
     *
     * @param graph    array presented graph
     * @param vertex   index of source vertex. Starts with 0
     * @param vertexTo index of target vertex. Starts with 0
     */
    public static void addDirectedNormalized(final int[][] graph, final int vertex, final int vertexTo) {
        addEdge(graph, vertex, vertexTo);
    }

    /**
     * Adds directed normalized edge to weighted graph.
     *
     * @param graph    array presented graph
     * @param vertex   index of source vertex. Starts with 0
     * @param vertexTo index of target vertex. Starts with 0
     * @param weight   weight of edge
     */
    public static void addDirectedNormalized(final int[][][] graph, final int vertex, final int vertexTo, final int weight) {
        addEdge(graph, vertex, vertexTo, weight);
    }

    protected static void addEdge(final int[][] graph, final int vFrom, final int vTo) {
        if (graph[vFrom] != null) {
            int length = graph[vFrom].length;
            for (int edgeIndex = 0; edgeIndex < length; edgeIndex++) {
                if (graph[vFrom][edgeIndex] == vTo) {
                    return;
                }
            }
            final int[] edges = new int[length + 1];
            System.arraycopy(graph[vFrom], 0, edges, 0, length);
            edges[length] = vTo;
            graph[vFrom] = edges;
        } else {
            graph[vFrom] = new int[]{vTo};
        }
    }

    protected static void addEdge(final int[][][] graph, final int vFrom, final int vTo, final int weight) {
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

    /**
     * Returns reverted unweighted graph.
     *
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
