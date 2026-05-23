package com.errday.codingtest.numbertheory;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class EulerPhi {


    @Test
    void case1() {
        int n = 15;
        int answer = 8;

        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 14;
        int answer = 6;

        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        int result = n;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }

                result -= result / i;
            }
        }

        if (n > 1) {
            result -= result / n;
        }

        return result;
    }
}
