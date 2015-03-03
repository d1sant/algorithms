package com.my.algorithms.week6.second;

import java.util.*;

public class DijkstraTask1 {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int vertexes = scanner.nextInt();
        final int edges = scanner.nextInt();
        final int[][][] graph = new int[vertexes][][];
        scanner.nextLine();
        for (int index = 0; index < edges; index++) {
            addDirected(graph, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
        final int vertexFrom = scanner.nextInt() - 1;
        final int vertexTo = scanner.nextInt() - 1;
        final Pair<int[], int[]> result = dijkstra(graph, vertexFrom); // from vertex 1 to vertex 3: 0 to 2
        final int distance = result.first[vertexTo];
        System.out.println(distance == Integer.MAX_VALUE ? -1 : distance);
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

    private static Pair<int[], int[]> dijkstra(final int[][][] graph, final int vertex) {

        final int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        final int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);
        distances[vertex] = 0;

        Collection<Edge> weightedDistances = new ArrayList<Edge>(distances.length);
        for (int vertexIndex = 0; vertexIndex < distances.length; vertexIndex++) {
            weightedDistances.add(new Edge(distances[vertexIndex], vertexIndex));
        }
        final PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(weightedDistances);
        while (!priorityQueue.isEmpty()) {
            final int vertexFrom = priorityQueue.poll().getVertex();
            if (graph[vertexFrom] != null) {
                for (final int[] edge: graph[vertexFrom]) {
                    final int vertexTo = edge[0];
                    final int weight = edge[1];
                    final int distanceTo = distances[vertexTo];
                    final long relaxedDistanceTo = (long) distances[vertexFrom] + weight;
                    if (distanceTo > relaxedDistanceTo) {
                        distances[vertexTo] = (int) relaxedDistanceTo;
                        previous[vertexTo] = vertexFrom;
                        priorityQueue.remove(new Edge(distanceTo, vertexTo));
                        priorityQueue.offer(new Edge(distances[vertexTo], vertexTo));
                    }
                }
            }
        }
        return new Pair<int[], int[]>(distances, previous);
    }

    private static class Edge implements Comparable<Edge> {

        final int weight;
        final int vertex;

        public Edge(int weight, int vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }

        public int getWeight() {
            return weight;
        }

        public int getVertex() {
            return vertex;
        }

        @Override
        public int compareTo(final Edge that) {
            return Integer.compare(this.weight, that.weight);
        }

        @Override
        public boolean equals(Object o) {
            final boolean isEqual;
            if (this == o) {
                isEqual = true;
            } else if (o == null || getClass() != o.getClass()) {
                isEqual = false;
            } else {
                Edge edge = (Edge) o;
                isEqual = this.vertex == edge.vertex && this.weight == edge.weight;
            }
            return isEqual;
        }

        @Override
        public int hashCode() {
            int result = weight;
            result = 31 * result + vertex;
            return result;
        }
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
}
