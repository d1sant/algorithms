package com.my.algorithms.week5.second;

import java.util.Arrays;

/**
 * Explore algorithm for traversing of all available vertexes in graph from defined vertex.
 */
public class Explore {

    public static void main(String[] args) {
        final int[][] graph = new int[][]{{1, 2}, {2}, {}};
        System.out.println("Visited from 0 vertex: " + Arrays.toString(explore(graph, 0)));
        System.out.println("Visited from 1 vertex: " + Arrays.toString(explore(graph, 1)));
        System.out.println("Visited from 2 vertex: " + Arrays.toString(explore(graph, 2)));
    }

    public static int[] explore(final int[][] graph, final int vertex) {
        final int[] visited = new int[graph.length];
        explore(graph, vertex, visited);
        return visited;
    }

    public static void explore(final int[][] graph, final int vertex, final int[] visited) {
        visited[vertex] = 1;
        if (graph[vertex] != null) {
            for (int edgeIndex = 0; edgeIndex < graph[vertex].length; edgeIndex++) {
                final int edge = graph[vertex][edgeIndex];
                if (visited[edge] == 0) {
                    explore(graph, edge, visited);
                }
            }
        }
    }
}
