package com.errday.codingtest.numbertheory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EuclideanAlgorithm {

    @Test
    void case1() {
        int a = 270;
        int b = 192;
        int answer = 6;

        assertThat(gcd(a, b)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int a = 300;
        int b = 165;
        int answer = 15;

        assertThat(gcd(a, b)).isEqualTo(answer);
    }

    private int gcd(int max, int min) {
        if (min == 0) return max;
        return gcd(min, max % min);
    }
}
