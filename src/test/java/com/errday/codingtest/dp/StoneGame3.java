package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoneGame3 {

    @Test
    void case1() {
        int n = 6;
        String answer = "SK";
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 7;
        String answer = "CY";
        assertThat(solution(n)).isEqualTo(answer);
    }

    private String solution(int n) {
        int[] dp = new int[1000 + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 1;
        dp[4] = 1;

        for (int i = 5; i <= n; i++) {
            if (dp[i - 1] == 1 && dp[i - 3] == 1 && dp[i - 4] == 1) {
                dp[i] = 2;
            } else {
                dp[i] = 1;
            }
        }

        return dp[n] == 1 ? "SK" : "CY";
    }
}
