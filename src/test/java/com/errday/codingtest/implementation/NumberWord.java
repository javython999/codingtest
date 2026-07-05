package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberWord {

    @Test
    void case1() {
        int n = 5;
        int answer = 5;
        assertThat(solution(n)).isEqualTo(answer);

    }

    @Test
    void case2() {
        int n = 15;
        int answer = 21;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 120;
        int answer = 252;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        int m = n;
        int position = 1;
        int sum = 0;

        for (int i = 10; i <= n; i *= 10) {
            sum += position * (i - (i / 10));
            position += 1;
            m -= (i - (i / 10));
        }

        sum += m * position;

        return sum;
    }
}
