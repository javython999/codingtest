package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RGBStreet {

    @Test
    void case1() {
        int[][] costs = {
                {26, 40, 83},
                {49, 60, 57},
                {13, 89, 99}
        };
        int answer = 96;
        assertThat(solution(costs)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] costs = {
                {1, 100, 100},
                {100, 1, 100},
                {100, 100, 1}
        };
        int answer = 3;
        assertThat(solution(costs)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[][] costs = {
                {1, 100, 100},
                {100, 100, 100},
                {1, 100, 100}
        };
        int answer = 102;
        assertThat(solution(costs)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int[][] costs = {
                {30, 19, 5},
                {64, 77, 64},
                {15, 19, 97},
                {4, 71, 57},
                {90, 86, 84},
                {93, 32, 91},
        };
        int answer = 208;
        assertThat(solution(costs)).isEqualTo(answer);
    }

    private int RED = 0;
    private int GREEN = 1;
    private int BLUE = 2;

    private int solution(int[][] costs) {
        int[][] memo = new int[costs.length][3];
        memo[0][RED] = costs[0][RED];
        memo[0][GREEN] = costs[0][GREEN];
        memo[0][BLUE] = costs[0][BLUE];

        for (int i = 1; i < costs.length; i++) {
            memo[i][RED] = Math.min(memo[i - 1][GREEN], memo[i - 1][BLUE]) + costs[i][RED];
            memo[i][GREEN] = Math.min(memo[i - 1][RED], memo[i - 1][BLUE]) + costs[i][GREEN];
            memo[i][BLUE] = Math.min(memo[i - 1][RED], memo[i - 1][GREEN]) + costs[i][BLUE];
        }

        return Arrays.stream(memo[costs.length - 1])
                .min()
                .orElse(-1);
    }
}
