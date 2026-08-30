package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class Plus123 {


    @Test
    void case1() {
        int n = 4;
        int answer = 3;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 7;
        int answer = 9;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 10;
        int answer = 27;
        assertThat(solution(n)).isEqualTo(answer);
    }


    private int solution(int targetNumber) {
        int mod = 1_000_000_009;

        int[][] memo = new int[targetNumber + 1][4];
        memo[1][1] = 1;
        memo[2][2] = 1;
        memo[3][1] = 1;
        memo[3][2] = 1;
        memo[3][3] = 1;

        for (int number = 4; number <= targetNumber; number++) {
            memo[number][1] = (memo[number - 1][2] + memo[number - 1][3]) % mod;
            memo[number][2] = (memo[number - 2][1] + memo[number - 2][3]) % mod;
            memo[number][3] = (memo[number - 3][1] + memo[number - 3][2]) % mod;
        }


        return (((memo[targetNumber][1] + memo[targetNumber][2]) % mod) + memo[targetNumber][3]) % mod;
    }
}
