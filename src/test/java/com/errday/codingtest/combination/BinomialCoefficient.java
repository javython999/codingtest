package com.errday.codingtest.combination;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BinomialCoefficient {

    @Test
    void case1() {
        int n = 5;
        int k = 2;
        int answer = 10;
        assertThat(solution(n, k)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 6;
        int k = 3;
        int answer = 20;
        assertThat(solution(n, k)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 7;
        int k = 2;
        int answer = 21;
        assertThat(solution(n, k)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 15;
        int k = 3;
        int answer = 455;
        assertThat(solution(n, k)).isEqualTo(answer);
    }

    private int solution(int n, int k) {
        int[][] dp = new int[n + 1][n + 1];

        for (int totalCount = 1; totalCount < n + 1; totalCount++) {
            dp[totalCount][0] = 1;
            dp[totalCount][totalCount] = 1;
            dp[totalCount][1] = totalCount;
        }

        for (int totalCount = 2; totalCount < n + 1; totalCount++) {
            for (int choose = 1; choose < totalCount; choose++) {
                dp[totalCount][choose] = dp[totalCount - 1][choose] + dp[totalCount - 1][choose - 1];
            }
        }

        return dp[n][k];
    }
}
