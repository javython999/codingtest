package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Change {

    @Test
    void case1() {
        int n = 13;
        int answer = 5;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 14;
        int answer = 4;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 8;
        int answer = 4;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {
        int coins = n / 5;
        n %= 5;

        if (n % 2 != 0) {
            coins -= 1;
            n += 5;
        }

        coins += n / 2;

        return coins;
    }
}
