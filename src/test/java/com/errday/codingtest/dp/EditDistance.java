package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EditDistance {

    @Test
    void case1() {
        String s1 = "horse";
        String s2 = "ros";
        int answer = 3;
        assertThat(solution(s1, s2)).isEqualTo(answer);
    }

    private int solution(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = i;
        }

        for (int pointer1 = 1; pointer1 < n + 1; pointer1++) {
            for (int pointer2 = 1; pointer2 < m + 1; pointer2++) {

                if (s1.charAt(pointer1 - 1) == s2.charAt(pointer2 - 1)) {
                    dp[pointer1][pointer2] = dp[pointer1 - 1][pointer2 - 1];
                } else {

                    int case1 = dp[pointer1][pointer2 - 1];
                    int case2 = dp[pointer1 - 1][pointer2];
                    int case3 = dp[pointer1 - 1][pointer2 - 1];

                    dp[pointer1][pointer2] = Math.min(case1, Math.min(case2, case3)) + 1;
                }

            }
        }


        return dp[n][m];
    }
}
