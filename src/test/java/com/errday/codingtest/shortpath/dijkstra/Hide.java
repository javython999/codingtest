package com.errday.codingtest.shortpath.dijkstra;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Hide {

    @Test
    void case1() {
        int n = 6;
        int[][] input = {
            {3, 6},
            {4, 3},
            {3, 2},
            {1, 3},
            {1, 2},
            {2, 4},
            {5, 2}
        };

        int[] answer = {4, 2, 3};

        assertThat(solution(n, input)).isEqualTo(answer);
    }

    private int[] solution(int n, int[][] input) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] info : input) {
            int start = info[0];
            int end = info[1];
            graph.get(start).add(new int[] {end, 1});
            graph.get(end).add(new int[] {start, 1});
        }


        int[] result = dijkstra(graph, 1);

        int maxDistance = -1;
        for (int vertex = 1; vertex < n + 1; vertex++) {
            maxDistance = Math.max(maxDistance, result[vertex]);
        }

        int maxDistanceCount = 0;
        for (int vertex = 1; vertex < n + 1; vertex++) {
            if (result[vertex] == maxDistance) {
                maxDistanceCount += 1;
            }
        }

        int maxDistanceSequence = 0;
        for (int sequence = 1; sequence < n + 1; sequence++) {
            if (result[sequence] == maxDistance) {
                maxDistanceSequence = sequence;
                break;
            }
        }

        return new int[] {maxDistanceSequence, maxDistance, maxDistanceCount};
    }

    private int[] dijkstra(List<List<int[]>> graph, int start) {
        int inf = Integer.MAX_VALUE / 2;

        int[] costs = new int[graph.size()];
        Arrays.fill(costs, inf);
        costs[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        queue.offer(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNumber = current[0];
            int weight = current[1];

            if (costs[currentNumber] < weight) {
                continue;
            }

            List<int[]> nexts = graph.get(currentNumber);
            for (int[] next : nexts) {
                int nextNumber = next[0];
                int nextWeight = next[1];
                int newCost = weight + nextWeight;

                if (newCost < costs[nextNumber]) {
                    costs[nextNumber] = newCost;
                    queue.offer(new int[]{nextNumber, newCost});
                }
            }
        }

        for (int i = 0; i < costs.length; i++) {
            if (costs[i] == inf) {
                costs[i] = -1;
            }
        }

        return costs;
    }
}
