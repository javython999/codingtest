package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Increase {

    @Test
    void case1() {
        int n = 1;
        int answer = 10;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 2;
        int answer = 55;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 3;
        int answer = 220;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {
        int mod = 10_007;
        int[][] dp = new int[n+1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            dp[i][0] = 1;

            for (int j = 1; j < 10; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j - 1]) % mod;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[n][i]) % mod;
        }

        return answer;
    }
}
