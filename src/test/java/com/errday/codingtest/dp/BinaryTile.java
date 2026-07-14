package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryTile {

    @Test
    void case1 () {
        int n = 4;
        long answer = 5;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2 () {
        int n = 5;
        long answer = 8;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3 () {
        int n = 15;
        long answer = 987;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int div = 15746;
    private long solution(int n) {
        long[] dp = new long[1_000_000 + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 5;

        for (int i = 5; i <= n; i++) {
            dp[i] = (dp[i - 1]+ dp[i - 2]) % div;
        }

        return dp[n];
    }
}
