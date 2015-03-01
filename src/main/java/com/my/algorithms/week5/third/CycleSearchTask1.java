package com.my.algorithms.week5.third;

import java.util.Scanner;

public class CycleSearchTask1 {

    public static void main(final String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final int vertexes = scanner.nextInt();
        final int edges = scanner.nextInt();
        final int[][] graph = new int[vertexes][];
        scanner.nextLine();
        for (int index = 0; index < edges; index++) {
            addDirected(graph, scanner.nextInt(), scanner.nextInt());
        }
        dfs(graph);
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

    private static WorkingTime[] dfs(final int[][] graph) {
        final WorkingTime[] visited = new WorkingTime[graph.length];
        final Time cycle = new Time(0);
        dfs(graph, visited, cycle);
        System.out.println(cycle.value > 0 ? 1 : 0);
        return visited;
    }

    private static void dfs(final int[][] graph, final WorkingTime[] times, Time cycle) {
        final Time time = new Time(0);
        for (int vertexIndex = 0; vertexIndex < graph.length; vertexIndex++) {
            if (times[vertexIndex] == null) {
                explore(graph, vertexIndex, times, time, cycle);
            }
        }
    }

    private static void explore(final int[][] graph, final int vertex, final WorkingTime[] times, final Time time, final Time cycle) {
        times[vertex] = new WorkingTime(time.incrementAndGet());
        if (graph[vertex] != null) {
            for (int edgeIndex = 0; edgeIndex < graph[vertex].length; edgeIndex++) {
                final int edge = graph[vertex][edgeIndex];
                if (times[edge] == null) {
                    explore(graph, edge, times, time, cycle);
                } else if (times[edge].end == 0) {
                    cycle.incrementAndGet();
                }
            }
        }
        times[vertex].setEnd(time.incrementAndGet());
    }

    private static class Time {

        private int value;

        public Time(int value) {
            this.value = value;
        }

        public int incrementAndGet() {
            return ++value;
        }
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
}
