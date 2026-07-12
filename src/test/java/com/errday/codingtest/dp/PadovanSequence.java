package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PadovanSequence {

    @Test
    void case1() {
        int n = 6;
        long answer = 3;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 12;
        long answer = 16;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private long solution(int n) {
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        dp[6] = 3;
        dp[7] = 4;
        dp[8] = 5;
        dp[9] = 7;
        dp[10] = 9;

        for (int i = 11; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        return dp[n];
    }
}
