package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NextPrimeNumber {

    @Test
    void case1() {
        long n = 6;
        long answer = 7;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        long n = 20;
        long answer = 23;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        long n = 100;
        long answer = 101;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        long n = 2085791452;
        long answer = 2085791459;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private long solution(long n) {

        while (true) {

            boolean isPrime = true;

            for (int i = 2; i < Math.sqrt(n); i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                return n;
            }

            n += 1;
        }

    }
}
