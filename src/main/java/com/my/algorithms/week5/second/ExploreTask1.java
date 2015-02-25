package com.my.algorithms.week5.second;

import java.util.Arrays;

public class ExploreTask1 {

    public static void main(String[] args) {
        final int[][] graph = new int[4][];
        addEdge(graph, 1, 2);
        addEdge(graph, 3, 2);
        addEdge(graph, 4, 3);
        System.out.println(Arrays.toString(graph));
    }

    public static void addEdge(final int[][] graph, final int firstVertex, final int secondVertex) {
        final int vertex;
        final int vertexForAdd;
        if (firstVertex < secondVertex) {
            vertex = firstVertex;
            vertexForAdd = secondVertex;
        } else {
            vertex = secondVertex;
            vertexForAdd = firstVertex;
        }
        int length = graph[vertex].length;
        for (int edgeIndex = 0; edgeIndex < length; edgeIndex++) {
           if (graph[vertex][edgeIndex] == vertexForAdd) {
               return;
           }
        }
        final int[] edges = new int[length + 1];
        System.arraycopy(graph[vertex], 0, edges, 0, length);
        edges[length] = vertexForAdd;
        graph[vertex] = edges;
    }
}
