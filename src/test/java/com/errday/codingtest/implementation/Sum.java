package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Sum {

    @Test
    void case1() {
        long s = 200;
        int answer = 19;
        assertThat(solution(s)).isEqualTo(answer);
    }

    @Test
    void case2() {
        long s = 55;
        int answer = 10;
        assertThat(solution(s)).isEqualTo(answer);
    }

    @Test
    void case3() {
        long s = 6;
        int answer = 3;
        assertThat(solution(s)).isEqualTo(answer);
    }

    private long solution(long s) {
        long sum = 0;
        long n = 1;

        while (sum <= s) {
            sum += n;

            if (sum > s) {
                n -= 1;
                break;
            }

            n += 1;
        }

        return n;
    }
}
