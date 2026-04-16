package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LCS2 {

    @Test
    void case1() {
        String s1 = "ACAYKP";
        String s2 = "CAPCAK";
        String answer = "ACAK";
        assertThat(solution(s1, s2)).isEqualTo(answer);
    }


    private String solution(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] memo = new int[n + 1][m + 1];

        for (int pointer1 = 1; pointer1 < n + 1; pointer1++) {
            for (int pointer2 = 1; pointer2 < m + 1; pointer2++) {

                if (s1.charAt(pointer1 - 1) == s2.charAt(pointer2 - 1)) {
                    memo[pointer1][pointer2] = memo[pointer1 - 1][pointer2 - 1] + 1;
                } else {
                    memo[pointer1][pointer2] = Math.max(memo[pointer1 - 1][pointer2], memo[pointer1][pointer2 - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int pointer1 = n;
        int pointer2 = m;

        while (pointer1 > 0 && pointer2 > 0) {

            if (s1.charAt(pointer1 - 1) == s2.charAt(pointer2 - 1)) {
                sb.append(s1.charAt(pointer1 - 1));
                pointer1 -= 1;
                pointer2 -= 1;
            } else {
                if (memo[pointer1][pointer2] == memo[pointer1 - 1][pointer2]) {
                    pointer1 -= 1;
                } else {
                    pointer2 -= 1;
                }
            }
        }

        return sb.reverse().toString();
    }
}
