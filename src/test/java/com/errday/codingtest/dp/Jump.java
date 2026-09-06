package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Jump {

    @Test
    void case1() {
        int n = 4;
        int[][] map = {
                {2, 3, 3, 1},
                {1, 2, 1, 3},
                {1, 2, 3, 1},
                {3, 1, 1, 0}
        };
        int answer = 3;
        assertThat(solution(n, map)).isEqualTo(answer);
    }

    private long solution(int n, int[][] map) {
        long[][] memo = new long[n][n];
        memo[0][0] = 1;


        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                int distance = map[row][col];

                if (distance == 0) {
                    continue;
                }

                if (row + distance < n) {
                    memo[row + distance][col] += memo[row][col];
                }

                if (col + distance < n) {
                    memo[row][col + distance] += memo[row][col];
                }

            }
        }

        return memo[n - 1][n - 1];
    }
}
