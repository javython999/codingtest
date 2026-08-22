package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Sticker {

    @Test
    void case1() {
        int [][] stickers = {
                {50, 10, 100, 20, 40},
                {30, 50, 70, 10, 60}
        };

        int answer = 260;
        assertThat(solution(stickers)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int [][] stickers = {
                {10, 30, 10, 50, 100, 20, 40},
                {20, 40, 30, 50, 60, 20, 80}
        };
        int answer = 290;
        assertThat(solution(stickers)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int [][] stickers = {
                {10},
                {20}
        };
        int answer = 20;
        assertThat(solution(stickers)).isEqualTo(answer);
    }

    private int solution(int[][] stickers) {
        int n = stickers[0].length;
        if (n == 1) {
            return Math.max(stickers[0][0], stickers[1][0]);
        }

        int[][] memo = new int[2][n];
        memo[0][0] = stickers[0][0];
        memo[1][0] = stickers[1][0];
        memo[0][1] = memo[1][0] + stickers[0][1];
        memo[1][1] = memo[0][0] + stickers[1][1];

        for (int i = 2; i < n; i++) {
            memo[0][i] = Math.max(memo[1][i - 1], memo[1][i - 2]) + stickers[0][i];
            memo[1][i] = Math.max(memo[0][i - 1], memo[0][i - 2]) + stickers[1][i];
        }

        return Math.max(memo[0][n - 1], memo[1][n - 1]);
    }
}
