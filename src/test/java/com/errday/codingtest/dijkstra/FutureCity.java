package com.errday.codingtest.dijkstra;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class FutureCity {

    private int n = 5;
    private int x = 4;
    private int k = 5;
    private int[][] data = {
            {1, 2},
            {1, 3},
            {1, 4},
            {2, 4},
            {3, 4},
            {3, 5},
            {4, 5},
    };
    private int answer = 3;

    @Test
    void solution() {

        int[][] graph = new int[n + 1][n + 1];
        for (int[] row : graph) {
            Arrays.fill(row, Integer.MAX_VALUE/2);
        }

        for (int[] info : data) {
            graph[info[0]][info[0]] = 0;
            graph[info[0]][info[1]] = 1;
            graph[info[1]][info[0]] = 1;
            graph[info[1]][info[1]] = 0;
        }

        graph[1][1] = 0;
        int[] costFromStart = shortestCostTable(1, n, graph);
        int toKCost = costFromStart[k];

        int[] costFromK = shortestCostTable(k, n, graph);
        int toXCost = costFromK[x];

        int myAnswer = -1;
        if (toXCost != Integer.MAX_VALUE/2) {
            myAnswer = toKCost + toXCost;
        }

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }

    private int[] shortestCostTable(int start, int nodeCount, int[][] graph) {
        int[] cost = new int[nodeCount + 1];
        Arrays.fill(cost, Integer.MAX_VALUE/2);

        boolean[] visited = new boolean[nodeCount + 1];

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(data -> data[1]));
        queue.offer(new int[] {start, 0});

        while (!queue.isEmpty()) {
            int[] currentNodeInfo = queue.poll();
            int currentNodeNumber = currentNodeInfo[0];
            int currentNodeCost = currentNodeInfo[1];

            if (visited[currentNodeNumber]) {
                continue;
            }

            visited[currentNodeNumber] = true;

            for (int next = 1; next <= nodeCount; next++) {
                if (graph[currentNodeNumber][next] == Integer.MAX_VALUE/2) {
                    continue;
                }

                int newCost = currentNodeCost + 1;

                if (cost[next] > newCost) {
                    cost[next] = newCost;
                    queue.offer(new int[] {next, newCost});
                }
            }
        }

        return cost;
    }
}
