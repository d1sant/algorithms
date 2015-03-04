package com.my.algorithms.week6.third;

import java.util.*;

public class BellmanFordExtendedTask2 {

    public static void main(final String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final int vertexes = scanner.nextInt();
        final int edges = scanner.nextInt();
        final int vertexFrom = scanner.nextInt() - 1;
        final int[][][] graph = new int[vertexes][][];
        scanner.nextLine();
        for (int index = 0; index < edges; index++) {
            addDirected(graph, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        final Triple<int[], int[], Boolean> bfsResult = bellmanFord(graph, vertexFrom);
        final Pair<Integer, int[][]> dfsResult = dfs(graph);
        final Set<Integer> cycledVertexes = bfsResult.third ? getCycledVertexes(bfsResult.second, dfsResult.first, dfsResult.second) : Collections.<Integer> emptySet();

        for (int i = 0; i < bfsResult.first.length; i++) {
            int distance = bfsResult.first[i];
            System.out.println(cycledVertexes.contains(i) ? "-" : (distance == Integer.MAX_VALUE ? "*" : distance ));
        }
    }

    private static void addDirected(final int[][][] graph, final int vertex, final int vertexTo, final int weight) {
        addEdge(graph, vertex - 1, vertexTo - 1, weight);
    }

    private static void addEdge(final int[][][] graph, final int vFrom, final int vTo, final int weight) {
        if (graph[vFrom] != null) {
            int length = graph[vFrom].length;
            for (int edgeIndex = 0; edgeIndex < length; edgeIndex++) {
                if (graph[vFrom][edgeIndex][0] == vTo && graph[vFrom][edgeIndex][1] == weight) {
                    return;
                }
            }
            final int[][] edges = new int[length + 1][];
            System.arraycopy(graph[vFrom], 0, edges, 0, length);
            edges[length] = new int[]{vTo, weight};
            graph[vFrom] = edges;
        } else {
            graph[vFrom] = new int[][]{{vTo, weight}};
        }
    }

    private static Triple<int[], int[], Boolean> bellmanFord(final int[][][] graph, final int vertex) {
        final int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        final int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);
        distances[vertex] = 0;
        boolean isNegativeCycle = false;
        for (int i = 0; i < graph.length + 1; i++) {
            boolean isRelaxed = false;
            for (int vertexFrom = 0; vertexFrom < graph.length; vertexFrom++) {
                final int[][] edges = graph[vertexFrom];
                if (edges != null) {
                    for (int[] edge : edges) {
                        final int vertexTo = edge[0];
                        final int weight = edge[1];
                        final long relaxedDistanceTo = (long) distances[vertexFrom] + weight;
                        if (distances[vertexTo] > relaxedDistanceTo) {
                            distances[vertexTo] = (int) relaxedDistanceTo;
                            previous[vertexTo] = vertexFrom;
                            isRelaxed = true;
                        }
                    }
                }
            }
            if (!isRelaxed) {
                break;
            }
            if (i == graph.length) {
                isNegativeCycle = true;
                break;
            }
        }
        return new Triple<int[], int[], Boolean>(distances, previous, isNegativeCycle);
    }

    private static Pair<Integer, int[][]> dfs(final int[][][] graph) {
        final WorkingTime[] visited = new WorkingTime[graph.length];
        final Counter cycleIndex = new Counter(0);
        final int[][] cycles = new int[graph.length][];
        dfs(graph, visited, cycleIndex, cycles);
        return new Pair<Integer, int[][]>(cycleIndex.get(), cycles);
    }

    private static void dfs(final int[][][] graph, final WorkingTime[] times, final Counter cycleIndex, final int[][] cycles) {
        final Counter time = new Counter(0);
        for (int vertexIndex = 0; vertexIndex < graph.length; vertexIndex++) {
            if (times[vertexIndex] == null) {
                explore(graph, vertexIndex, times, time, cycleIndex, cycles);
            }
        }
    }

    private static void explore(final int[][][] graph, final int vertex, final WorkingTime[] times, final Counter time, final Counter cycleIndex, final int[][] cycles) {
        times[vertex] = new WorkingTime(time.incrementAndGet());
        if (graph[vertex] != null) {
            for (int vertexToIndex = 0; vertexToIndex < graph[vertex].length; vertexToIndex++) {
                final int vertexTo = graph[vertex][vertexToIndex][0];
                if (times[vertexTo] == null) {
                    explore(graph, vertexTo, times, time, cycleIndex, cycles);
                } else if (times[vertexTo].getEnd() == 0) {
                    cycles[cycleIndex.getAndIncrement()] = new int[] {vertex, vertexTo};
                }
            }
        }
        times[vertex].setEnd(time.incrementAndGet());
    }

    private static Set<Integer> getCycledVertexes(int[] previous, final int cycleNumber, int[][] cycles) {
        final Set<Integer> cycledVertexes = new HashSet<Integer>();
        for (int index = 0; index < cycleNumber; index++) {
            final Set<Integer> cycle = new HashSet<Integer>();
            final int vertexFrom = cycles[index][0];
            cycle.add(vertexFrom);
            int i = 0;
            int vertex = vertexFrom;
            while (i < previous.length) {
                vertex = previous[vertex];
                if (cycle.contains(vertex)) {
                    break;
                } else {
                    cycle.add(vertex);
                }
                i++;
            }
            cycledVertexes.addAll(cycle);
        }
        return cycledVertexes;
    }

    private static class Pair<F, S> {

        public final F first;
        public final S second;

        public Pair(final F first, final S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "{i=" + first + ", j=" + second + "}";
        }
    }

    private static class Triple<F, S, T> {

        public final F first;
        public final S second;
        public final T third;

        public Triple(F first, S second, T third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {
            return "{i=" + first + ", j=" + second + ", k=" + third + "}";
        }
    }

    private static class Counter {

        private int value;

        public Counter(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }

        public int incrementAndGet() {
            return ++value;
        }

        public int getAndIncrement() {
            return value++;
        }
    }

    private static class WorkingTime {

        private int start;
        private int end;

        public WorkingTime(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
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
