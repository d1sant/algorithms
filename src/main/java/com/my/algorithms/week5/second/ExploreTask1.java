package com.my.algorithms.week5.second;

import java.util.Arrays;

import static com.my.algorithms.week5.second.Explore.explore;

public class ExploreTask1 {

    public static void main(String[] args) {
        final int[][] graph = new int[4][];
        add(graph, 1, 2);
        add(graph, 3, 2);
        add(graph, 4, 3);
        System.out.println(toString(graph));
        search(graph, 1, 4);
    }

    public static void add(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
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

    public static String toString(final int[][] graph) {
        final StringBuilder result = new StringBuilder();
        for (int[] edges : graph) {
            result.append("[").append(Arrays.toString(edges)).append("]");
        }
        return result.toString();
    }

    public static void search(final int[][] graph, final int from, final int to) {
        final int[] visited = explore(graph, from - 1);
        System.out.println(visited[to - 1] == 1 ? 1 : 0);
    }
}
