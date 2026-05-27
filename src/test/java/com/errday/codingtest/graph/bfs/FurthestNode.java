package com.errday.codingtest.graph.bfs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FurthestNode {

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

        int answer = 3;
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    private int solution(int n, int[][] edge) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] info : edge) {
            int from = info[0];
            int to = info[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        boolean[] visited = new boolean[n + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {1, 0});
        visited[1] = true;

        int[] distances = new int[n + 1];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int distance = current[1];
            distances[currentNode] = distance;

            List<Integer> nexts = graph.get(currentNode);
            for (int next : nexts) {

                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                queue.offer(new int[] {next, distance + 1});
            }
        }

        int maxDistance = Arrays.stream(distances)
                .max()
                .getAsInt();

        return Arrays.stream(distances)
                .filter(distance -> distance == maxDistance)
                .toArray()
                .length;
    }
}
