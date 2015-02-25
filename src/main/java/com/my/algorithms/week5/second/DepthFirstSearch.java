package com.my.algorithms.week5.second;

import java.util.Arrays;

import static com.my.algorithms.week5.second.Explore.explore;

/**
 * Depth first search algorithm.
 */
public class DepthFirstSearch {

    public static void main(String[] args) {
        final int[][] graph = new int[][]{{1, 2}, {2}, {}};
        System.out.println("Visited vertexes: " + Arrays.toString(dsg(graph)));
    }

    public static int[] dsg(final int[][] graph) {
        final int[] visited = new int[graph.length];
        dsg(graph, visited);
        return visited;
    }

    public static void dsg(final int[][] graph, final int[] visited) {
        for (int vertexIndex = 0; vertexIndex < graph.length; vertexIndex++) {
            if (visited[vertexIndex] == 0) {
                explore(graph, vertexIndex, visited);
            }
        }
    }
}
