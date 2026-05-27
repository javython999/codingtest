package com.errday.codingtest.topologysort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TopologySort {

    @Test
    void case1() {
        int n = 7;
        int[][] datas = {
                {1, 2},
                {1, 5},
                {2, 3},
                {2, 6},
                {3, 4},
                {4, 7},
                {5, 6},
                {6, 4}
        };
        int[] answer = {1, 2, 5, 3, 6, 4, 7};
        assertThat(solution(n, datas)).isEqualTo(answer);
    }

    private int[] solution(int n, int[][] datas) {
        int[] indegrees = new int[n + 1];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] data : datas) {
            int from = data[0];
            int to = data[1];
            graph.get(from).add(to);
            indegrees[to] += 1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[n];
        int resultIndex = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[resultIndex++] = current;

            List<Integer> nexts = graph.get(current);
            for (int next : nexts) {
                indegrees[next] -= 1;
                if (indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result;
    }
}
