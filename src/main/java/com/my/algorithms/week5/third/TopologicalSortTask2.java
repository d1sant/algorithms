package com.my.algorithms.week5.third;

import java.util.Scanner;

public class TopologicalSortTask2 {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int vertexes = scanner.nextInt();
        final int edges = scanner.nextInt();
        final int[][] graph = new int[vertexes][];
        scanner.nextLine();
        for (int index = 0; index < edges; index++) {
            addDirected(graph, scanner.nextInt(), scanner.nextInt());
        }
        System.out.println(getSortedAsString(topSort(graph)));
    }

    private static void addDirected(final int[][] graph, final int firstVertex, final int secondVertex) {
        addEdge(graph, firstVertex - 1, secondVertex - 1);
    }

    private static void addEdge(final int[][] graph, final int firstVertex, final int secondVertex) {
        if (graph[firstVertex] != null) {
            int length = graph[firstVertex].length;
            for (int edgeIndex = 0; edgeIndex < length; edgeIndex++) {
                if (graph[firstVertex][edgeIndex] == secondVertex) {
                    return;
                }
            }
            final int[] edges = new int[length + 1];
            System.arraycopy(graph[firstVertex], 0, edges, 0, length);
            edges[length] = secondVertex;
            graph[firstVertex] = edges;
        } else {
            graph[firstVertex] = new int[]{secondVertex};
        }
    }

    private static int[] topSort(int[][] graph) {
        final int[] sorted = new int[graph.length];
        dfs(graph, sorted);
        return sorted;
    }

    private static void dfs(final int[][] graph, final int[] sorted) {
        dfs(graph, new WorkingTime[graph.length], sorted);
    }

    private static void dfs(final int[][] graph, final WorkingTime[] times, final int[] sorted) {
        final Counter time = new Counter(0);
        final Counter sortedIndex = new Counter(graph.length);
        for (int vertexIndex = 0; vertexIndex < graph.length; vertexIndex++) {
            if (times[vertexIndex] == null) {
                explore(graph, vertexIndex, times, time, sorted, sortedIndex);
            }
        }
    }

    private static void explore(final int[][] graph, final int vertex, final WorkingTime[] times, final Counter time, final int[] sorted, final Counter sortedIndex) {
        times[vertex] = new WorkingTime(time.incrementAndGet());
        if (graph[vertex] != null) {
            for (int edgeIndex = 0; edgeIndex < graph[vertex].length; edgeIndex++) {
                final int edge = graph[vertex][edgeIndex];
                if (times[edge] == null) {
                    explore(graph, edge, times, time, sorted, sortedIndex);
                }
            }
        }
        times[vertex].setEnd(time.incrementAndGet());
        sorted[sortedIndex.decrementAndGet()] = vertex;
    }

    private static String getSortedAsString(final int[] sorted) {
        final StringBuilder result = new StringBuilder();
        for (final int vertex : sorted) {
            result.append(vertex + 1).append(' ');
        }
        return result.toString();
    }

    private static class WorkingTime {

        private int start;
        private int end;

        public WorkingTime(int start) {
            this.start = start;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "{" + start + ", " + end + '}';
        }
    }

    private static class Counter {

        private int value;

        public Counter(int value) {
            this.value = value;
        }


        public int incrementAndGet() {
            return ++value;
        }

        public int decrementAndGet() {
            return --value;
        }
    }
}
