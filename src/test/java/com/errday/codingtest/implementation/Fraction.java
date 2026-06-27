package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Fraction {

    @Test
    void case1() {
        int n = 14;
        String answer = "2/4";
        assertThat(solution(n)).isEqualTo(answer);
    }


    @Test
    void case2() {
        int n = 2;
        String answer = "1/2";
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 3;
        String answer = "2/1";
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 4;
        String answer = "3/1";
        assertThat(solution(n)).isEqualTo(answer);
    }

    private String solution(int n) {

        int squre = 1;

        int x = 1;
        int y = 1;



        return x + "/" + y;
    }
}
