package com.errday.codingtest.dijkstra;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Telegram {

    private int n = 3;
    private int m = 2;
    private int c = 1;
    private int[][] data = {
            {1, 2, 4},
            {1, 3, 2},
    };
    private int[] answer = {2, 4};
    private final int INF = Integer.MAX_VALUE / 2;

    @Test
    void solution() {
        int[][] graph = new int[n + 1][n + 1];
        for (int[] node : graph) {
            Arrays.fill(node, INF);
        }

        for (int[] row : data) {
            graph[row[0]][row[1]] = row[2];
        }

        int[] costFromStart = djikstra(graph, c);

        int messageReceiveCount = Arrays.stream(costFromStart)
                .filter(cost -> cost != INF && cost != 0)
                .toArray()
                .length;

        int maxTime = Arrays.stream(costFromStart)
                .filter(cost -> cost != INF)
                .max()
                .getAsInt();

        Assertions.assertThat(new int[] {messageReceiveCount, maxTime}).isEqualTo(answer);
    }

    public int[] djikstra(int[][] graph, int start) {
        int length = graph.length;

        int[] cost = new int[length];
        Arrays.fill(cost, INF);
        cost[start] = 0;

        boolean[] visited = new boolean[length];


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

            for (int next = 1; next < length; next++) {

                int newCost = currentNodeCost + graph[currentNodeNumber][next];

                if (cost[next] > newCost) {
                    cost[next] = newCost;
                    queue.offer(new int[] {next, newCost});
                }
            }
        }

        return cost;
    }
}
