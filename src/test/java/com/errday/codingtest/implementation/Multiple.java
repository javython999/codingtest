package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Multiple {

    @Test
    void case1() {
        int a = 10;
        int b = 11;
        int c = 12;
        long answer = 4;
        Assertions.assertThat(solution(a, b, c)).isEqualTo(answer);
    }

    private int mod;
    private long solution(int a, int b, int c) {
        mod = c;
        return pow(a, b);
    }

    private long pow(long a, long b) {
        if (b == 1) {
            return a % mod;
        }

        long half = pow(a, b / 2) % mod;

        long result = half * half % mod;

        if (b % 2 == 1) {
            result = (result * a) % mod;
        }

        return result;
    }
}
