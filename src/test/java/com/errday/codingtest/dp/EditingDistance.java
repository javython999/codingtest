package com.errday.codingtest.dp;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EditingDistance {

    @Test
    void case1() {
        String a = "cat";
        String b = "cut";

        int answer = 1;

        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String a = "sunday";
        String b = "saturday";

        int answer = 3;

        assertThat(solution(a, b)).isEqualTo(answer);
    }

    private int solution(String a, String b) {

        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {

                if (a.charAt(row - 1) == b.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    int left = dp[row][col - 1];
                    int leftUp = dp[row - 1][col - 1];
                    int up = dp[row - 1][col];
                    int result = Math.min(left, Math.min(leftUp, up)) + 1;
                    dp[row][col] = result;
                }

            }
        }

        for (int[] memo : dp) {
            System.out.println(Arrays.toString(memo));
        }

        return dp[n][m];
    }
}
