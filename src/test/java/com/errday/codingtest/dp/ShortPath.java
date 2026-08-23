package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ShortPath {


    @Test
    void case1() {
        int distance = 150;
        int[][] highway = {
                {0, 50, 10},
                {0, 50, 20},
                {50, 100, 10},
                {100, 151, 10},
                {110, 140, 90}
        };

        int answer = 70;
        assertThat(solution(distance, highway)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int distance = 100;
        int[][] highway = {
                {10, 60, 40},
                {50, 90, 20}
        };

        int answer = 80;
        assertThat(solution(distance, highway)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int distance = 900;
        int[][] highway = {
                {0, 10, 9},
                {20, 60, 45},
                {80, 190, 100},
                {50, 70, 15},
                {160, 180, 14},
                {140, 160, 14},
                {420, 901, 5},
                {450, 900, 0}
        };

        int answer = 432;
        assertThat(solution(distance, highway)).isEqualTo(answer);
    }

    private int solution(int distance, int[][] highway) {
        int[] dp = new int[distance+1];
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        dp[0] = 0;

        for (int move = 0; move <= distance; move++) {

            if (move > 0) {
                dp[move] = Math.min(dp[move], dp[move-1] + 1);
            }

            for (int[] info : highway) {
                int start = info[0];
                int end = info[1];
                int cost = info[2];

                if (start == move && end <= distance) {
                    dp[end] = Math.min(dp[end], dp[move] + cost);
                }

            }

        }



        return dp[distance];
    }
}
