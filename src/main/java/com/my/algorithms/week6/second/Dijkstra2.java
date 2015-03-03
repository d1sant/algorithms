package com.my.algorithms.week6.second;

import com.my.algorithms.domain.dto.Pair;
import com.my.algorithms.tools.Graphs;
import com.my.algorithms.week6.second.Dijkstra.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;

import static com.my.algorithms.tools.Graphs.addDirected;

/**
 * Dijkstra algorithm. Looks for the shortest ways from given vertex in weighted graph.
 * Uses build in PriorityQueue.
 */
public class Dijkstra2 {

    public static void main(final String[] args) {

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
}
