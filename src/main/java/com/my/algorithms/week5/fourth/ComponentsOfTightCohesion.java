package com.my.algorithms.week5.fourth;

import com.my.algorithms.domain.Counter;
import com.my.algorithms.domain.WorkingTime;
import com.my.algorithms.tools.Graphs;

import java.util.Arrays;
import java.util.HashSet;

import static com.my.algorithms.tools.Graphs.addDirected;
import static com.my.algorithms.tools.Graphs.reverse;

/**
 * Components of tight cohesion algorithm.
 */
public class ComponentsOfTightCohesion {

    public static void main(String[] args) {

        final int[][] graph = new int[4][];
        addDirected(graph, 1, 2);
        addDirected(graph, 4, 1);
        addDirected(graph, 2, 3);
        addDirected(graph, 3, 1);

        System.out.println("Graph: " + Graphs.toString(graph));
        System.out.println("Reversed graph: " + Graphs.toString(reverse(graph)));
        System.out.println("Number of components: " + search(graph));
    }

    private static int search(int[][] graph) {

        final int[] sorted = new int[graph.length];
        dfs(reverse(graph), sorted);

        final Integer[] visited = new Integer[graph.length];
        int component = 1;
        for(int sortedIndex = sorted.length - 1; sortedIndex >= 0; sortedIndex--) {
            final int vertex = sorted[sortedIndex];
            if (visited[vertex] == null) {
                explore(graph, vertex, visited, component);
            }
            component++;
        }
        return new HashSet<Integer>(Arrays.asList(visited)).size();
    }

    private static void dfs(final int[][] graph, final int[] sorted) {
        dfs(graph, new WorkingTime[graph.length], sorted);
    }

    private static void dfs(final int[][] graph, final WorkingTime[] times, final int[] sorted) {
        final Counter time = new Counter(0);
        final Counter sortedIndex = new Counter(0);
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
        sorted[sortedIndex.getAndIncrement()] = vertex;
    }

    private static void explore(final int[][] graph, final int vertex, final Integer[] visited, final int component) {
        visited[vertex] = component;
        if (graph[vertex] != null) {
            for (int edgeIndex = 0; edgeIndex < graph[vertex].length; edgeIndex++) {
                final int edge = graph[vertex][edgeIndex];
                if (visited[edge] == null) {
                    explore(graph, edge, visited, component);
                }
            }
        }
    }
}
