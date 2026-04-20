package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Knapsack {

    @Test
    void case1() {
        int n = 4;
        int k = 7;
        int[][] items = {{6, 13}, {4, 8}, {3, 6}, {5, 12}};
        int answer = 14;
        assertThat(solution_1d(n, k, items)).isEqualTo(answer);
        assertThat(solution_2d(n, k, items)).isEqualTo(answer);
    }

    private int solution_2d(int n, int k, int[][] items) {

        int[][] memo = new int[n + 1][k + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int w = 1; w < k + 1; w++) {

                int[] item = items[i - 1];

                if (w >= item[0]) {
                    memo[i][w] = Math.max(
                            memo[i - 1][w],
                            memo[i - 1][w - item[0]] + item[1]
                    );
                } else {
                    memo[i][w] = memo[i - 1][w];
                }
            }
        }

        return memo[n][k];
    }

    private int solution_1d(int n, int k, int[][] items) {

        int[] memo = new int[k + 1];

        for (int i = 0; i < n; i++) {

            int[] item = items[i];

            for (int weight = k; weight >= item[0]; weight--) {
                memo[weight] = Math.max(memo[weight], memo[weight - item[0]] + item[1]);
            }
        }


        return memo[k];
    }
}
