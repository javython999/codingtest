package com.errday.codingtest.graph.bellmanford;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeMachine {

    @Test
    void case1() {
        int n = 3;
        int m = 4;
        int[][] input = {
                {1, 2, 4},
                {1, 3, 3},
                {2, 3, -1},
                {3, 1, -2}
        };
        boolean answer = true;

        assertThat(solution(n, m, input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 3;
        int m = 4;
        int[][] input = {
                {1, 2, 4},
                {1, 3, 3},
                {2, 3, -4},
                {3, 1, -2}
        };

        boolean answer = false;

        assertThat(solution(n, m, input)).isEqualTo(answer);
    }

    private int INF = Integer.MAX_VALUE / 2;

    private boolean solution(int n, int m, int[][] input) {

        long[] distances = new long[n + 1];
        Arrays.fill(distances, INF);
        distances[1] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                int[] edge = input[j];
                int from = edge[0];
                int to = edge[1];
                int weight = edge[2];

                long newDistance = distances[from] + weight;
                if (distances[from] != INF && newDistance < distances[to]) {
                    distances[to] = newDistance;
                }
            }
        }

        for (int j = 0; j < m; j++) {
            int[] edge = input[j];
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            long newDistance = distances[from] + weight;
            if (distances[from] != INF && newDistance < distances[to]) {
                return false;
            }
        }

        return true;
    }
}

