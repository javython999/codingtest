package com.errday.codingtest.topologysort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LineUp {

    @Test
    void case1() {
        int n = 4;
        int m = 2;
        int[][] input = {
                {4, 2},
                {3, 1},
                {4, 3}
        };
        int[] answer = {4, 2, 3, 1};

        assertThat(solution(n, m, input)).containsExactly(answer);
    }

    @Test
    void case2() {
        int n = 3;
        int m = 2;
        int[][] input = {
                {1, 3},
                {2, 3}
        };
        int[] answer = {1, 2, 3};

        assertThat(solution(n, m, input)).containsExactly(answer);
    }

    private int[] solution(int n, int m, int[][] input) {

        int[] indegrees = new int[n + 1];

        Map<Integer,ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : input) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
        }

        for (int i = 1; i < n + 1; i++) {
            ArrayList<Integer> nexts = graph.get(i);
            for (int next : nexts) {
                indegrees[next] += 1;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            graph.get(current)
                    .forEach(next -> {
                        indegrees[next] -= 1;
                        if (indegrees[next] == 0) {
                            queue.offer(next);
                        }
                    });
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
