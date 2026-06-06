package com.errday.codingtest.dp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tile3N {

    @Test
    void case1() {
        int n = 2;
        int answer = 3;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int answer = 11;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 6;
        int answer = 41;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        if (n % 2 != 0) {
            return 0;
        }

        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[2] = 3;


        for (int i = 4; i < n + 1; i+= 2) {
            dp[i] = (dp[i - 2] * 3) % 100_000_007;

            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] = (dp[i] + (dp[j] * 2)) % 100_000_007;
            }
        }

        return (int) dp[n];
    }
}
