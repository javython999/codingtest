package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringReverse {

    @Test
    void case1() {
        String source = "0011000";
        int answer = 1;
        assertThat(solution(source)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String source = "1101110";
        int answer = 2;
        assertThat(solution(source)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String source = "0100011";
        int answer = 2;
        assertThat(solution(source)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String source = "1111111";
        int answer = 0;
        assertThat(solution(source)).isEqualTo(answer);
    }

    private int solution(String source) {
        String[] split = source.split("");

        int changeTo0 = 0;
        int changeTo1 = 0;

        if ("0".equals(split[0])) {
            changeTo1 += 1;
        } else if ("1".equals(split[0])) {
            changeTo0 += 1;
        }


        for (int i = 0; i < split.length - 1; i++) {
            String current = split[i];
            String next = split[i + 1];

            if (!current.equals(next)) {
                if ("0".equals(next)) {
                    changeTo1 += 1;
                } else {
                    changeTo0 += 1;
                }
            }

        }

        return Math.min(changeTo0, changeTo1);
    }
}
