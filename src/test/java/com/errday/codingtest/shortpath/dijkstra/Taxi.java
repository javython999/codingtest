package com.errday.codingtest.shortpath.dijkstra;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Taxi {

    private final int INF = Integer.MAX_VALUE / 2;

    @Test
    void case1() {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {
                {4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25}
        };
        int answer = 82;
        assertThat(solution(n, s, a, b, fares)).isEqualTo(answer);
    }

    private int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] graph = initGraph(n, fares);

        int[] distFromStart = dijkstra(graph, s);
        int[] distFromA = dijkstra(graph, a);
        int[] distFromB = dijkstra(graph, b);

        int answer = distFromStart[a] + distFromStart[b];

        for (int i = 1; i < n + 1; i++) {
            int newCost = distFromStart[i] + distFromA[i] + distFromB[i];
            answer = Math.min(answer, newCost);
        }

        return answer;
    }

    private int[][] initGraph(int nodeCount, int[][] fares) {
        int[][] graph = new int[nodeCount+1][nodeCount+1];
        for (int[] row : graph) {
            Arrays.fill(row, INF);
        }

        for (int[] fare : fares) {
            int a = fare[0];
            int b = fare[1];
            int cost = fare[2];
            graph[a][a] = 0;
            graph[b][b] = 0;
            graph[a][b] = cost;
            graph[b][a] = cost;
        }

        return graph;
    }

    private int[] dijkstra(int[][] graph, int start) {
        int nodeCount = graph.length;
        int[] costs = new int[nodeCount];
        Arrays.fill(costs, INF);
        costs[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        queue.offer(new int[] {start, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentVertex = current[0];
            int currentCost = current[1];

            if (costs[currentVertex] < currentCost) {
                continue;
            }

            int[] nexts = graph[currentVertex];

            for (int next = 1; next < nexts.length; next++) {
                int nextCost = nexts[next];

                if (nextCost == INF) {
                    continue;
                }

                int newCost = currentCost + nextCost;

                if (newCost < costs[next]) {
                    costs[next] = newCost;
                    queue.offer(new int[] {next, newCost});
                }
            }
        }

        return costs;
    }
}
