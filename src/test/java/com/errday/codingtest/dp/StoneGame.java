package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoneGame {

    @Test
    void case1() {
        int n = 5;
        String answer = "SK";
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 3;
        String answer = "SK";
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 2;
        String answer = "CY";
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 1;
        String answer = "SK";
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case5() {
        int n = 100;
        String answer = "CY";
        assertThat(solution(n)).isEqualTo(answer);
    }


    private String solution(int n) {
        if (n == 1) {
            return "SK";
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            if (dp[i - 1] == 1 || dp[i - 3] == 1) {
                dp[i] = 2;
            } else {
                dp[i] = 1;
            }
        }
        return dp[n] == 1 ? "SK" : "CY";
    }
}
