package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tile2N {

    @Test
    void case1() {
        int n = 2;
        int answer = 2;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 9;
        int answer = 55;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 6;
        int answer = 13;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 1000;
        int answer = 282;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n] % 10007;
    }
}
