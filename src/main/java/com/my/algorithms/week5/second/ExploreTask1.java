package com.my.algorithms.week5.second;

import java.util.Scanner;

public class ExploreTask1 {

    public static void main(final String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final int vertexes = scanner.nextInt();
        final int edges = scanner.nextInt();
        final int[][] graph = new int[vertexes][];
        scanner.nextLine();
        for (int index = 0; index < edges; index++) {
            addUndirected(graph, scanner.nextInt(), scanner.nextInt());
        }
        search(graph, scanner.nextInt(), scanner.nextInt());
    }

    private static void addUndirected(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
        addEdge(graph, secondVertex - 1, firstVertex - 1);
    }

    private static void addEdge(final int[][] graph, final int vertexFrom, final int vertexTo) {
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

    public static void search(final int[][] graph, final int from, final int to) {
        final int[] visited = explore(graph, from - 1);
        System.out.println(visited[to - 1] == 1 ? 1 : 0);
    }

    private static int[] explore(final int[][] graph, final int vertex) {
        final int[] visited = new int[graph.length];
        explore(graph, vertex, visited);
        return visited;
    }

    private static void explore(final int[][] graph, final int vertex, final int[] visited) {
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
