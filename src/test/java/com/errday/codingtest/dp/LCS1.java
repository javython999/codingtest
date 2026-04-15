package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LCS1 {

    @Test
    void case1() {
        String s1 = "ACAYKP";
        String s2 = "CAPCAK";
        int answer = 4;
        assertThat(solution(s1, s2)).isEqualTo(answer);
    }

    private int solution(String s1, String s2) {

        int s1Length = s1.length();
        int s2Length = s2.length();

        int[][] memo = new int[s1Length + 1][s2Length +1];

        for (int i = 1; i < s1Length + 1; i++) {
            for (int j = 1; j < s2Length + 1; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i][j - 1], memo[i - 1][j]);
                }

            }
        }

        return memo[s1Length][s2Length];
    }
}
