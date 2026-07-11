package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Plus {

    @Test
    void case1() {
        int n = 4;
        int answer = 7;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 7;
        int answer = 44;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 10;
        int answer = 274;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {
        int[][] dp = new int[n+1][n+1];
        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[1][3] = 1;

        for (int useCount = 2; useCount < n+1; useCount++) {
            for (int targetNumber = 1; targetNumber < n+1; targetNumber++) {
                dp[useCount][targetNumber] += dp[useCount-1][targetNumber-1];

                if (1 < targetNumber) {
                    dp[useCount][targetNumber] += dp[useCount-1][targetNumber-2];
                }

                if (2 < targetNumber) {
                    dp[useCount][targetNumber] += dp[useCount-1][targetNumber-3];
                }
            }
        }

        int[] s = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                s[i] += dp[j][i];
            }
        }

        return s[n];
    }
}
