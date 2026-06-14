package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Stick {

    @Test
    void case1() {
        int n = 23;
        int answer = 4;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 32;
        int answer = 1;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 64;
        int answer = 1;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 48;
        int answer = 2;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {
        int count = 0;
        for (char c : Integer.toBinaryString(n).toCharArray()) {
            if (c == '1') {
                count += 1;
            }
        }
        return count;
    }
}
