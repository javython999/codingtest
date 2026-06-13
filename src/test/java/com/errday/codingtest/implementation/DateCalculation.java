package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateCalculation {

    @Test
    void case1() {
        int e = 1;
        int s = 16;
        int m = 16;
        int answer = 16;
        assertThat(solution(e, s, m)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int e = 1;
        int s = 2;
        int m = 3;
        int answer = 5266;
        assertThat(solution(e, s, m)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int e = 15;
        int s = 28;
        int m = 19;
        int answer = 7980;
        assertThat(solution(e, s, m)).isEqualTo(answer);
    }

    private int solution(int e, int s, int m) {
        if (e == 15) {
            e = 0;
        }

        if (s == 28) {
            s = 0;
        }

        if (m == 19) {
            m = 0;
        }

        int year = 0;
        int earth = 0;
        int sun = 0;
        int moon = 0;

        while (true) {
            earth = (earth + 1) % 15;
            sun = (sun + 1) % 28;
            moon = (moon + 1) % 19;
            year += 1;

            if (e == earth && s == sun && m == moon) {
                break;
            }
        }

        return year;
    }

}
