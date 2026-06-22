package com.errday.codingtest.bruteforce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SugarDelivery {

    @Test
    void case1() {
        int n = 18;
        int answer = 4;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int answer = -1;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 6;
        int answer = 2;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 9;
        int answer = 3;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case5() {
        int n = 11;
        int answer = 3;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case6() {
        int n = 22;
        int answer = 6;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {
        int remainder = n;
        int sugarBag = 0;

        while (remainder >= 0) {
            if (remainder % 5 == 0) {
                return sugarBag + (remainder / 5);
            }

            remainder -= 3;
            sugarBag += 1;
        }

        return -1;
    }
}
