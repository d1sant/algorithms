package com.my.algorithms.week6.second;

import com.my.algorithms.domain.dto.Pair;
import com.my.algorithms.tools.Graphs;
import com.my.algorithms.week3.second.Heap;
import com.my.algorithms.week3.second.MinBinaryHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.my.algorithms.tools.Graphs.addDirected;

/**
 * Dijkstra algorithm. Looks for the shortest ways from given vertex in weighted graph.
 */
public class Dijkstra {

    public static void main(final String[] args) {

        final int[][][] wGraph = new int[5][][];
        wGraph[0] = new int[][]{{1, 4}, {2, 2}};
        wGraph[1] = new int[wGraph[0].length + 1][];
        System.arraycopy(wGraph[0], 0, wGraph[1], 0, wGraph[0].length);
        wGraph[1][2] = new int[]{5, 1};

        printEdge(wGraph, 0, 0);
        printEdge(wGraph, 0, 1);
        printEdge(wGraph, 1, 0);
        printEdge(wGraph, 1, 1);
        printEdge(wGraph, 1, 2);
        System.out.println("\nWeighted Graph: " + Graphs.toString(wGraph) + "\n");

        final int[][][] wGraphCopy = new int[5][][];
        System.arraycopy(wGraph, 0, wGraphCopy, 0, wGraph.length);

        printEdge(wGraphCopy, 0, 0);
        printEdge(wGraphCopy, 0, 1);
        printEdge(wGraphCopy, 1, 0);
        printEdge(wGraphCopy, 1, 1);
        printEdge(wGraphCopy, 1, 2);
        System.out.println("\nWeighted Graph: " + Graphs.toString(wGraphCopy));

        final int[][][] multiWGraph = new int[2][][];
        addDirected(multiWGraph, 1, 2, 1);
        addDirected(multiWGraph, 1, 2, 5);
        addDirected(multiWGraph, 2, 1, 3);
        System.out.println("\nMulti Weighted Graph: " + Graphs.toString(multiWGraph));

        final int[][][] graph = new int[5][][];
        addDirected(graph, 1, 2, 4);
        addDirected(graph, 1, 3, 2);
        addDirected(graph, 2, 3, 3);
        addDirected(graph, 2, 4, 2);
        addDirected(graph, 2, 5, 3);
        addDirected(graph, 3, 2, 1);
        addDirected(graph, 3, 4, 4);
        addDirected(graph, 3, 5, 5);
        addDirected(graph, 5, 4, 1);
        System.out.println("\nWeighted Graph: " + Graphs.toString(graph) + "\n");

        final int[][][] dGraph = new int[4][][];
        addDirected(dGraph, 1, 2, 1);
        addDirected(dGraph, 4, 1, 2);
        addDirected(dGraph, 2, 3, 2);
        addDirected(dGraph, 1, 3, 5);
        System.out.println("Weighted Graph: " + Graphs.toString(dGraph));
        final Pair<int[], int[]> result = dijkstra(dGraph, 0); // from vertex 1 to vertex 3: 0 to 2
        System.out.println("Distances: " + Arrays.toString(result.first));
        System.out.println("Previous: " + Arrays.toString(result.second));
        System.out.println("Distance from 1 to 3 is equaled " + result.first[2]);
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
        final Heap<Edge> priorityQueue = new MinBinaryHeap<Edge>(weightedDistances);
        while (!priorityQueue.isEmpty()) {
            final int vertexFrom = priorityQueue.extract().getVertex();
            if (graph[vertexFrom] != null) {
                for (final int[] edge: graph[vertexFrom]) {
                    final int vertexTo = edge[0];
                    final int weight = edge[1];
                    final int distanceTo = distances[vertexTo];
                    final long relaxedDistanceTo = (long) distances[vertexFrom] + weight;
                    if (distanceTo > relaxedDistanceTo) {
                        distances[vertexTo] = (int) relaxedDistanceTo;
                        previous[vertexTo] = vertexFrom;
                        priorityQueue.change(new Edge(distanceTo, vertexTo), new Edge(distances[vertexTo], vertexTo));
                    }
                }
            }
        }
        return new Pair<int[], int[]>(distances, previous);
    }

    private static void printEdge(int[][][] wGraph, int vFrom, int vTo) {
        System.out.println("Edge [" + vFrom + ", " + wGraph[vFrom][vTo][0] + "]: " + wGraph[vFrom][vTo][1]);
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
}
