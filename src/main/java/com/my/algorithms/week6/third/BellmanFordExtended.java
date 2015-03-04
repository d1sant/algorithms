package com.my.algorithms.week6.third;

import com.my.algorithms.domain.Counter;
import com.my.algorithms.domain.WorkingTime;
import com.my.algorithms.domain.dto.Pair;
import com.my.algorithms.domain.dto.Triple;
import com.my.algorithms.tools.Graphs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.my.algorithms.tools.Graphs.addDirected;
import static com.my.algorithms.week6.third.BellmanFord.MAX_VALUE_LONG;
import static com.my.algorithms.week6.third.BellmanFord.bellmanFord;

/**
 * Bellman-Ford algorithm complemented with depth first search for finding cycles.
 *
 * Я проверял достижимость из циклов отрицательного веса тем, что прогонял алгоритм лишних несколько раз, запоминая, какие вершины меняют веса, и повторял так, пока список таких вершин не переставал увеличиваться.
 * Потом я заменил эти лишние прогоны одноразовыми обходами графа из изменившихся в последней итерации вершин, отмечая достижимые вершины. После этого задача прошла все тесты.
 */
public class BellmanFordExtended {

    public static void main(final String[] args) {

        final long[][][] graph = new long[6][][];
        addDirected(graph, 1, 2, 10);
        addDirected(graph, 2, 3, 5);
        addDirected(graph, 1, 3, 100);
        addDirected(graph, 3, 5, 7);
        addDirected(graph, 5, 4, 10);
        addDirected(graph, 4, 3, -18);
        addDirected(graph, 6, 1, -1);

        processAndPrintln(graph, 0); // 0 10 - - - *

        processAndPrint("5 5 1  1 3 -5  3 2 2  2 1 1  4 3 3  3 5 1"); // - - - * -

        processAndPrint("3 3 2  1 1 -1  1 2 2  2 3 3"); // * 0 3

        processAndPrint("4 5 1  1 2 1000  2 3 -2  3 2 1  3 4 10000  1 4 1"); // 0 - - -

        // processAndPrint("6 6 1  1 3 1  5 1 -10  3 5 10  4 2 13  6 4 -10  2 6 -10"); // 0 * 1 * 11 *

        processAndPrint("7 8 4  1 2 -1  2 3 -1  3 1 -1  5 6 -1  6 7 -1  7 5 -1  4 1 -1  4 5 -1"); // - - - 0 - - -

        processAndPrint("8 8 1  1 2 3  2 3 6  5 3 1  3 4 8  6 4 10  6 7 -5  7 8 1  8 6 2"); // 0 3 9 17 * * * *

    }

    private static Pair<long[][][], Integer> parse(final String input) {
        final String[] params = input.split("\\s+");
        final long[][][] graph = new long[Integer.valueOf(params[0])][][];
        final int vertexFrom = Integer.valueOf(params[2]) - 1;
        int index = 3;
        while (index < params.length) {
            addDirected(graph, Integer.valueOf(params[index]), Integer.valueOf(params[index + 1]), Integer.valueOf(params[index + 2]));
            index = index + 3;
        }
        return new Pair<long[][][], Integer>(graph, vertexFrom);
    }

    private static void processAndPrint(final String inputStr) {
        processAndPrint(parse(inputStr));
    }

    private static void processAndPrint(Pair<long[][][], Integer> input) {
        final String[] result = process(input.first, input.second);
        final StringBuilder output = new StringBuilder();
        for (String str : result) {
            output.append(str).append(' ');
        }
        output.deleteCharAt(output.length() - 1);
        System.out.println(output.toString());
    }

    private static void processAndPrintln(final long[][][] graph, final int vertexFrom) {
        final String[] result = process(graph, vertexFrom);
        for (final String str : result) {
            System.out.println(str);
        }
    }

    private static String[] process(long[][][] graph, final int vertexFrom) {

        System.out.println("\nWeighted Graph: " + Graphs.toString(graph));
        final Triple<long[], int[], Boolean> bfsResult = bellmanFord(graph, vertexFrom);
        System.out.println("Distances: " + Arrays.toString(bfsResult.first));
        System.out.println("Previous: " + Arrays.toString(bfsResult.second));
        System.out.println("Is there a negative cycle: " + bfsResult.third + "\n");

        final Pair<Integer, int[][]> dfsResult = dfs(graph);
        final Set<Integer> cycledVertexes = bfsResult.third ? getCycledVertexes(bfsResult.second, dfsResult.first, dfsResult.second) : Collections.<Integer> emptySet();

        final String[] result = new String[graph.length];
        for (int i = 0; i < bfsResult.first.length; i++) {
            final long distance = bfsResult.first[i];
            result[i] = cycledVertexes.contains(i) ? "-" : (distance == MAX_VALUE_LONG ? "*" : String.valueOf(distance));
        }
        return result;
    }

    private static Pair<Integer, int[][]> dfs(final long[][][] graph) {
        final WorkingTime[] visited = new WorkingTime[graph.length];
        final Counter cycleIndex = new Counter(0);
        final int[][] cycles = new int[graph.length][];
        dfs(graph, visited, cycleIndex, cycles);
        return new Pair<Integer, int[][]>(cycleIndex.get(), cycles);
    }

    private static void dfs(final long[][][] graph, final WorkingTime[] times, final Counter cycleIndex, final int[][] cycles) {
        final Counter time = new Counter(0);
        for (int vertexIndex = 0; vertexIndex < graph.length; vertexIndex++) {
            if (times[vertexIndex] == null) {
                explore(graph, vertexIndex, times, time, cycleIndex, cycles);
            }
        }
    }

    private static void explore(final long[][][] graph, final int vertex, final WorkingTime[] times, final Counter time, final Counter cycleIndex, final int[][] cycles) {
        times[vertex] = new WorkingTime(time.incrementAndGet());
        if (graph[vertex] != null) {
            for (int vertexToIndex = 0; vertexToIndex < graph[vertex].length; vertexToIndex++) {
                final int vertexTo = (int) graph[vertex][vertexToIndex][0];
                if (times[vertexTo] == null) {
                    explore(graph, vertexTo, times, time, cycleIndex, cycles);
                } else if (times[vertexTo].getEnd() == 0) {
                    cycles[cycleIndex.getAndIncrement()] = new int[] {vertex, vertexTo};
                }
            }
        }
        times[vertex].setEnd(time.incrementAndGet());
    }

    private static Set<Integer> getCycledVertexes(int[] previous, final int cycleNumber, int[][] cycles) {
        final Set<Integer> cycledVertexes = new HashSet<Integer>();
        for (int index = 0; index < cycleNumber; index++) {
            final Set<Integer> cycle = new HashSet<Integer>();
            final int vertexFrom = cycles[index][0];
            cycle.add(vertexFrom);
            int i = 0;
            int vertex = vertexFrom;
            while (i < previous.length) {
                vertex = previous[vertex];
                if (cycle.contains(vertex)) {
                    break;
                } else {
                    cycle.add(vertex);
                }
                i++;
            }
            cycledVertexes.addAll(cycle);
        }
        return cycledVertexes;
    }

}
