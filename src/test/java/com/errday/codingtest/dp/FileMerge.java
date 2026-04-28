package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class FileMerge {

    @Test
    void case1() {
        int n = 4;
        int[] files = {40, 30, 30, 50};
        int answer = 300;

        assertThat(solution(n, files)).isEqualTo(answer);
    }

    private int solution(int n, int[] files) {

        int[] sum = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            sum[i] = sum[i - 1] + files[i - 1];
        }

        int[][] memo = new int[n + 1][n + 1];

        for (int length = 2; length < n + 1; length++) {
            for (int i = 1; i + length - 1 < n + 1; i++) {

                int j = i + length - 1;
                memo[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = memo[i][k] + memo[k + 1][j] + (sum[j] - sum[i - 1]);
                    memo[i][j] = Math.min(memo[i][j], cost);
                }

            }
        }

        return memo[1][n];
    }
}
