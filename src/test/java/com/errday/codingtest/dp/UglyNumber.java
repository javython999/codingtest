package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UglyNumber {

    @Test
    void case1() {
        int sequence = 10;

        int answer = 12;

        assertThat(solution(sequence)).isEqualTo(answer);
    }

    private int solution(int sequence) {

        int[] dp = new int[sequence];
        dp[0] = 1;

        int pointer2 = 0;
        int pointer3 = 0;
        int pointer5 = 0;

        for (int i = 1; i < sequence; i++) {
            int next2 = dp[pointer2] * 2;
            int next3 = dp[pointer3] * 3;
            int next5 = dp[pointer5] * 5;

            int nextUglyNumber = Math.min(next2, Math.min(next3, next5));
            dp[i] = nextUglyNumber;

            if (nextUglyNumber == next2) {
                pointer2 += 1;
            }

            if (nextUglyNumber == next3) {
                pointer3 += 1;
            }

            if (nextUglyNumber == next5) {
                pointer5 += 1;
            }
        }


        return dp[sequence - 1];
    }
}
