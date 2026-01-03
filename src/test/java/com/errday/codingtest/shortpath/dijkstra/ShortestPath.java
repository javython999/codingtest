package com.errday.codingtest.shortpath.dijkstra;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ShortestPath {

    private int n = 6;
    private int[][] graph = {
            {1, 2, 2},
            {1, 3, 5},
            {1, 4, 1},
            {2, 3, 3},
            {2, 4, 2},
            {3, 2, 3},
            {3, 6, 5},
            {4, 3, 3},
            {4, 5, 1},
            {5, 3, 1},
            {5, 6, 2},
    };
    private int start = 1;
    private int[] answer = {0, 2, 3, 1, 2, 4};

    @Test
    void solution() {

        int[][] distances = new int[n + 1][n + 1];
        for (int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }

        for (int[] graphInfo : graph) {
            int startNode = graphInfo[0];
            int endNode = graphInfo[1];
            int cost = graphInfo[2];
            distances[startNode][startNode] = 0;
            distances[startNode][endNode] = cost;
        }

        int[] cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(data -> data[1]));
        queue.offer(new int[] {start, 0});

        boolean[] visited = new boolean[n  + 1];

        while (!queue.isEmpty()) {
            int[] currentNodeInfo = queue.poll();
            int currentNodeNumber = currentNodeInfo[0];
            int weight = currentNodeInfo[1];

            if (visited[currentNodeNumber]) {
                continue;
            }

            visited[currentNodeNumber] = true;

            for (int next = 1; next < distances[currentNodeNumber].length; next++) {
                if (distances[currentNodeNumber][next] == Integer.MAX_VALUE) {
                    continue;
                }

                int newCost = weight + distances[currentNodeNumber][next];
                if (cost[next] > newCost) {
                    cost[next] = newCost;
                    queue.offer(new int[] {next, newCost});
                }

            }

        }

        int[] myAnswer = Arrays.copyOfRange(cost, 1, cost.length);

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }
}
