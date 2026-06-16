package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LCM {

    @Test
    void case1() {
        int a = 1;
        int b = 1;
        int answer = 1;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int a = 3;
        int b = 5;
        int answer = 15;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int a = 1;
        int b = 123;
        int answer = 123;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int a = 121;
        int b = 199;
        int answer = 24079;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    private int solution(int a, int b) {
        return (a * b) / euclidean(Math.max(a, b), Math.min(a, b));
    }

    private int euclidean(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return euclidean(b, a % b);
    }
}
