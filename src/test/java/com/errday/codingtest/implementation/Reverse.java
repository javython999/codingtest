package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Reverse {

    @Test
    void case1() {
        String input = "0001100";
        int answer = 1;
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String input = "11111";
        int answer = 0;
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String input = "0000001";
        int answer = 1;
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String input = "1100110011001100001";
        int answer = 4;
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case5() {
        String input = "11101101";
        int answer = 2;
        assertThat(solution(input)).isEqualTo(answer);
    }

    private int solution(String input) {

        char[] chars = input.toCharArray();
        int toZeroCount = 0;
        int toOneCount = 0;

        if (chars[0] == '0') {
            toOneCount += 1;
        }

        if (chars[0] == '1') {
            toZeroCount += 1;
        }

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                if (chars[i] == '0') {
                    toOneCount += 1;
                } else {
                    toZeroCount += 1;
                }
            }
        }

        return Math.min(toZeroCount, toOneCount);
    }
}
