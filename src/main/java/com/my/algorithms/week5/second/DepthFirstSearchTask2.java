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
            add(graph, scanner.nextInt(), scanner.nextInt());
        }
        System.out.println(new HashSet<Integer>(Arrays.asList(dsg(graph))).size());
    }

    private static void add(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
    }

    private static void addEdge(final int[][] graph, final int firstVertex, final int secondVertex) {
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

    private static Integer[] dsg(final int[][] graph) {
        final Integer[] visited = new Integer[graph.length];
        dsg(graph, visited);
        return visited;
    }

    private static void dsg(final int[][] graph, final Integer[] visited) {
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
