package com.my.algorithms.week5.third;

import com.my.algorithms.domain.Counter;
import com.my.algorithms.domain.WorkingTime;
import com.my.algorithms.tools.Graphs;

import java.util.Arrays;

import static com.my.algorithms.tools.Graphs.addDirected;

/**
 * Cycle search in directed graphs. Modified version of Depth First Search algorithm implementation.
 */
public class CycleSearch {

    public static void main(final String[] args) {

        final int[][] graph = new int[4][];
        addDirected(graph, 1, 2);
        addDirected(graph, 4, 1);
        addDirected(graph, 2, 3);
        addDirected(graph, 3, 1);
        System.out.println("Graph: " + Graphs.toString(graph));
        System.out.println("Working times: " + Arrays.toString(dfs(graph)) + "\n");

        final int[][] graph2 = new int[7][];
        addDirected(graph2, 1, 2);
        addDirected(graph2, 1, 3);
        addDirected(graph2, 2, 3);
        addDirected(graph2, 2, 4);
        addDirected(graph2, 3, 4);
        addDirected(graph2, 5, 1);
        addDirected(graph2, 5, 6);
        addDirected(graph2, 6, 7);
        addDirected(graph2, 5, 7);
        System.out.println("Graph: " + Graphs.toString(graph2));
        System.out.println("Working times: " + Arrays.toString(dfs(graph2)));
    }

    private static WorkingTime[] dfs(final int[][] graph) {
        final WorkingTime[] visited = new WorkingTime[graph.length];
        final Counter cycle = new Counter(0);
        dfs(graph, visited, cycle);
        System.out.println("Number of cycles: " + cycle.get());
        return visited;
    }

    private static void dfs(final int[][] graph, final WorkingTime[] times, Counter cycle) {
        final Counter time = new Counter(0);
        for (int vertexIndex = 0; vertexIndex < graph.length; vertexIndex++) {
            if (times[vertexIndex] == null) {
                explore(graph, vertexIndex, times, time, cycle);
            }
        }
    }

    private static void explore(final int[][] graph, final int vertex, final WorkingTime[] times, final Counter time, final Counter cycle) {
        times[vertex] = new WorkingTime(time.incrementAndGet());
        if (graph[vertex] != null) {
            for (int edgeIndex = 0; edgeIndex < graph[vertex].length; edgeIndex++) {
                final int edge = graph[vertex][edgeIndex];
                if (times[edge] == null) {
                    explore(graph, edge, times, time, cycle);
                } else if (times[edge].getEnd() == 0) {
                    System.out.println("fromVertex: " + vertex + ", toVertex: " + edge);
                    cycle.incrementAndGet();
                }
            }
        }
        times[vertex].setEnd(time.incrementAndGet());
    }
}
