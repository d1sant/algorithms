package com.my.algorithms.week6.second;

import com.my.algorithms.domain.dto.Pair;
import com.my.algorithms.tools.Graphs;
import com.my.algorithms.week3.second.Heap;
import com.my.algorithms.week3.second.MinBinaryHeap;

import java.util.Arrays;

import static com.my.algorithms.tools.Graphs.addDirected;

/**
 * Dijkstra algorithm. Looks for the shortest ways from given vertex in weighted graph.
 */
public class Dijkstra {

    public static void main(final String[] args) {

        final int[][][] wGraph = new int[5][][];
        wGraph[0] = new int[][] {{1, 4}, {2, 2}};
        wGraph[1] = new int[wGraph[0].length + 1][];
        System.arraycopy(wGraph[0], 0, wGraph[1], 0, wGraph[0].length);
        wGraph[1][2] = new int[] {5, 1};

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
    }

    private static Pair<int[], int[]> dijkstra(final int[][][] graph, final int vertex) {
        final int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        final int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);
        distances[vertex] = 0;
        final Heap<Integer> priorityQueue = new MinBinaryHeap<Integer>(graph.length); // TODO initialize it with array of distances

        return new Pair<int[], int[]>(distances, previous);
    }

    private static void printEdge(int[][][] wGraph, int vFrom, int vTo) {
        System.out.println("Edge [" + vFrom + ", " + wGraph[vFrom][vTo][0] + "]: " + wGraph[vFrom][vTo][1]);
    }
}
