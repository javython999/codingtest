package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ContinuousSum {

    @Test
    void case1() {
        int[] seq = {10, -4, 3, 1, 5, 6, -35, 12, 21, -1};
        int answer = 33;
        assertThat(solution(seq)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] seq = {2, 1, -4, 3, 4, -4, 6, 5, -5, 1};
        int answer = 14;
        assertThat(solution(seq)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] seq = {-1, -2, -3, -4, -5};
        int answer = -1;
        assertThat(solution(seq)).isEqualTo(answer);
    }

    private int solution(int[] seq) {
        int[] dp = new int[seq.length];
        dp[0] = seq[0];

        for (int i = 1; i < seq.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = seq[i];
                continue;
            }

            dp[i] = dp[i - 1] + seq[i];
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}

