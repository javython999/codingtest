package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AtoB {

    @Test
    void case1() {
        int a = 2;
        int b = 162;
        int answer = 5;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int a = 4;
        int b = 42;
        int answer = -1;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int a = 100;
        int b = 40021;
        int answer = 5;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    private int solution(long a, long b) {
        int calcCount = 1;

        while (a < b) {
            if (b % 2 == 0) {
                b /= 2;
            } else if (b % 10 == 1) {
                b /= 10;
            } else {
                return -1;
            }

            calcCount += 1;
        }

        return b == a ? calcCount : -1;
    }
}
