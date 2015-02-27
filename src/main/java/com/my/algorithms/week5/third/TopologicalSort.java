package com.my.algorithms.week5.third;

import com.my.algorithms.tools.Graphs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import static com.my.algorithms.tools.Graphs.addDirected;

/**
 * Topological sort algorithm using counting of degrees.
 */
public class TopologicalSort {

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
        final int[] degrees = new int[graph.length];
        for (int[] edges : graph) {
            if (edges != null) {
                for (int vertexTo : edges) {
                    degrees[vertexTo]++;
                }
            }
        }
        System.out.println("Degrees: " + Arrays.toString(degrees));
        final Deque<Integer> queue = new LinkedList<Integer>();
        for (int vertex = 0; vertex < degrees.length; vertex++) {
            if (degrees[vertex] == 0) {
                queue.offer(vertex);
            }
        }
        processQueue(graph, degrees, queue, sorted);
        System.out.println("Degrees after queuing: " + Arrays.toString(degrees));
        return sorted;
    }

    private static void processQueue(final int[][] graph, final int[] degrees, final Deque<Integer> queue, final int[] sorted) {
        int sortedIndex = 0;
        while(queue.size() > 0) {
            final int vertex = queue.poll();
            sorted[sortedIndex++] = vertex;
            final int[] edges = graph[vertex];
            if (edges != null) {
                for (int vertexTo : edges) {
                    if (degrees[vertexTo] - 1 == 0) {
                        degrees[vertexTo] = 0;
                        queue.push(vertexTo);
                    } else if (degrees[vertexTo] > 1) {
                        degrees[vertexTo]--;
                    }
                }
                System.out.println("Processed vertex [" + vertex + "]: " + Arrays.toString(degrees));
            }
        }
    }
}
