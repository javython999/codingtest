package com.errday.codingtest.graph.dijkstra;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Delivery {

    @Test
    void case1() {
        int n = 5;
        int k = 3;
        int[][] input = {
                {1, 2, 1},
                {2, 3, 3},
                {5, 2, 2},
                {1, 4, 2},
                {5, 3, 1},
                {5, 3, 2}
        };
        int answer = 4;

        assertThat(solution(n, k, input)).isEqualTo(answer);
    }
    @Test
    void case2() {
        int n = 6;
        int k = 4;
        int[][] input = {
                {1, 2, 1},
                {1, 3, 2},
                {2, 3, 2},
                {3, 4, 3},
                {3, 5, 2},
                {3, 5 ,3}
        };
        int answer = 4;

        assertThat(solution(n, k, input)).isEqualTo(answer);
    }


    private int solution(int n, int k, int[][] input) {
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] info : input) {
            int from = info[0];
            int to = info[1];
            int cost = info[2];

            graph[from].add(new int[] {to, cost});
            graph[to].add(new int[] {from, cost});
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[1] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        queue.offer(new int[] {1, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (distances[currentNode] < currentDistance) {
                continue;
            }

            List<int[]> nexts = graph[currentNode];
            for (int[] next : nexts) {
                int nextNode = next[0];
                int nextDistance = next[1];

                int newDistance = currentDistance + nextDistance;
                if (newDistance < distances[nextNode]) {
                    distances[nextNode] = newDistance;
                    queue.offer(new int[] {nextNode, newDistance});
                }
            }

        }

        return (int) Arrays.stream(distances)
                .filter(distance -> distance <= k)
                .count();
    }
}
