package com.my.algorithms.week6.first;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BreathFirstSearchTask1 {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int vertexes = scanner.nextInt();
        final int edges = scanner.nextInt();
        final int[][] graph = new int[vertexes][];
        scanner.nextLine();
        for (int index = 0; index < edges; index++) {
            addUndirected(graph, scanner.nextInt(), scanner.nextInt());
        }
        final int vertexFrom = scanner.nextInt() - 1;
        final int vertexTo = scanner.nextInt() - 1;
        final int[] distances = bfs(graph, vertexFrom);
        System.out.println(distances[vertexTo] == Integer.MAX_VALUE ? -1 : distances[vertexTo]);
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

    public static int[] bfs(final int[][] graph, final int vertexFrom) {
        final int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[vertexFrom] = 0;
        final Deque<Integer> queue = new LinkedList<Integer>();
        queue.offer(vertexFrom);
        while (queue.size() > 0) {
            final int vertex = queue.poll();
            if (graph[vertex] != null) {
                for (final int vertexTo : graph[vertex]) {
                    if (distances[vertexTo] == Integer.MAX_VALUE) {
                        queue.offer(vertexTo);
                        distances[vertexTo] = distances[vertex] + 1;
                    }
                }
            }
        }
        return distances;
    }
}
