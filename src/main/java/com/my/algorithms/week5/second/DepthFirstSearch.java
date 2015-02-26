package com.my.algorithms.week5.second;

import java.util.Arrays;

import static com.my.algorithms.week5.second.Explore.explore;

/**
 * Depth first search algorithm.
 */
public class DepthFirstSearch {

    public static void main(final String[] args) {
        final int[][] graph = new int[][]{{1, 2}, {2}, {}};
        System.out.println("Visited vertexes: " + Arrays.toString(dfs(graph)));
    }

    public static int[] dfs(final int[][] graph) {
        final int[] visited = new int[graph.length];
        dfs(graph, visited);
        return visited;
    }

    public static void dfs(final int[][] graph, final int[] visited) {
        for (int vertexIndex = 0; vertexIndex < graph.length; vertexIndex++) {
            if (visited[vertexIndex] == 0) {
                explore(graph, vertexIndex, visited);
            }
        }
    }
}
