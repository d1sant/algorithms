package com.my.algorithms.week5.third;

import com.my.algorithms.domain.Counter;
import com.my.algorithms.domain.WorkingTime;
import com.my.algorithms.tools.Graphs;

import java.util.Arrays;

import static com.my.algorithms.tools.Graphs.addDirected;

/**
 * Topological search algorithm using depth first search.
 */
public class TopologicalSortDfs {

    public static void main(String[] args) {

        final int[][] graph = new int[6][];
        addDirected(graph, 1, 3);
        addDirected(graph, 1, 5);
        addDirected(graph, 1, 6);
        addDirected(graph, 5, 6);
        addDirected(graph, 5, 4);
        addDirected(graph, 6, 4);
        addDirected(graph, 3, 2);
        addDirected(graph, 2, 4);

        System.out.println("Graph: " + Graphs.toString(graph));
        System.out.println("Topologically sorted vertexes: " + Arrays.toString(topSort(graph)));
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
}
