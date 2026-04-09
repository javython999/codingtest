package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MakeIt1 {

    @Test
    void case1() {
        int x = 2;
        int answer = 1;
        assertThat(solution(x)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int x = 10;
        int answer = 3;
        assertThat(solution(x)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int x = 45;
        int answer = 5;
        assertThat(solution(x)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int x = 7;
        int answer = 3;
        assertThat(solution(x)).isEqualTo(answer);
    }

    private int solution(int n) {

        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 0;

        for (int i = 2; i <= n; i++) {

            memo[i] = memo[i - 1] + 1;

            if (i % 2 == 0) {
                memo[i] = Math.min(memo[i], memo[i / 2] + 1);
            }

            if (i % 3 == 0) {
                memo[i] = Math.min(memo[i], memo[i / 3] + 1);
            }

        }

        return memo[n];
    }
}
