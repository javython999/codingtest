package com.errday.codingtest.dfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class NumberOfConnectionElements {

    @Test
    void case1() {
        int n = 6;
        int[][] input = {
                {1, 2},
                {2, 5},
                {5, 1},
                {3, 4},
                {4, 6}
        };
        int answer = 2;
        Assertions.assertThat(solution(n, input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 6;
        int[][] input = {
                {1, 2},
                {2, 5},
                {5, 1},
                {3, 4},
                {4, 6},
                {5, 4},
                {2, 4},
                {2, 3}
        };
        int answer = 1;
        Assertions.assertThat(solution(n, input)).isEqualTo(answer);
    }

    private int solution(int nodeCount, int[][] input) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < nodeCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : input) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        boolean[] visited = new boolean[nodeCount + 1];

        int connectCount = 0;

        for (int node = 1; node < nodeCount + 1; node++) {

            if (visited[node]) {
                continue;
            }

            connectCount += 1;

            ArrayDeque<Integer> stack = new ArrayDeque<>();
            stack.push(node);

            while(!stack.isEmpty()) {
                int current = stack.pop();

                for (int next : graph.get(current)) {
                    if (visited[next]) {
                        continue;
                    }

                    visited[next] = true;
                    stack.push(next);
                }
            }

        }

        return connectCount;
    }
}
