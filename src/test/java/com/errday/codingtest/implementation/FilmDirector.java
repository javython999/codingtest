package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FilmDirector {

    @Test
    void case1() {
        int n = 2;
        int answer = 1666;

        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 3;
        int answer = 2666;

        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        int count = 0;
        int number = 666;

        while (count <= n) {

            if (String.valueOf(number).contains("666")) {
                count += 1;
            }

            if (count < n) {
                number += 1;
            }
        }


        return number;
    }
}
