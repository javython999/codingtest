package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Thirty {

    @Test
    void case1() {
        int n = 30;
        int answer = 30;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 102;
        int answer = 210;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 2931;
        int answer = -1;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 80875542;
        int answer = 88755420;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);

        if (chars[0] != '0') {
            return -1;
        }

        int sumOfDigits = 0;
        for (char c : chars) {
            sumOfDigits += c - '0';
        }
        if (sumOfDigits % 3 != 0) {
            return -1;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }

        return Integer.parseInt(sb.reverse().toString());
    }


}
