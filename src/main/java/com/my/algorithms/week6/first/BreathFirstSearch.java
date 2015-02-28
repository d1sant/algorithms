package com.my.algorithms.week6.first;

import com.my.algorithms.tools.Graphs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import static com.my.algorithms.tools.Graphs.addUndirected;

/**
 * Breath First Search algorithm.
 */
public class BreathFirstSearch {

    public static void main(final String[] args) {

        final int[][] graph = new int[6][];
        addUndirected(graph, 1, 2);
        addUndirected(graph, 2, 3);
        addUndirected(graph, 3, 4);
        addUndirected(graph, 4, 1);
        addUndirected(graph, 1, 5);
        addUndirected(graph, 5, 6);
        addUndirected(graph, 6, 1);

        System.out.println("Graph: " + Graphs.toString(graph));
        System.out.println("Distances: " + Arrays.toString(bfs(graph, 0)));
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
