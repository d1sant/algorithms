package com.my.algorithms.week5.second;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class DepthFirstSearchTask2 {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int vertexes = scanner.nextInt();
        final int edges = scanner.nextInt();
        final int[][] graph = new int[vertexes][];
        scanner.nextLine();
        for (int index = 0; index < edges; index++) {
            addUndirected(graph, scanner.nextInt(), scanner.nextInt());
        }
        System.out.println(new HashSet<Integer>(Arrays.asList(dfs(graph))).size());
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

    private static Integer[] dfs(final int[][] graph) {
        final Integer[] visited = new Integer[graph.length];
        dfs(graph, visited);
        return visited;
    }

    private static void dfs(final int[][] graph, final Integer[] visited) {
        int cComponent = 1;
        for (int vertexIndex = 0; vertexIndex < graph.length; vertexIndex++) {
            if (visited[vertexIndex] == null) {
                explore(graph, vertexIndex, visited, cComponent);
            }
            cComponent++;
        }
    }

    private static void explore(final int[][] graph, final int vertex, final Integer[] visited, final int cComponent) {
        visited[vertex] = cComponent;
        if (graph[vertex] != null) {
            for (int edgeIndex = 0; edgeIndex < graph[vertex].length; edgeIndex++) {
                final int edge = graph[vertex][edgeIndex];
                if (visited[edge] == null) {
                    explore(graph, edge, visited, cComponent);
                }
            }
        }
    }
}
