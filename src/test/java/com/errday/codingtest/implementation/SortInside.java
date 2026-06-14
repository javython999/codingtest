package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SortInside {

    @Test
    void case1() {
        int n = 2143;
        int answer = 4321;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 999998999;
        int answer = 999999998;

        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 123456799;
        int answer = 997654321;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {
        int[] numbers = new int[10];

        char[] chars = String.valueOf(n).toCharArray();

        for (char c : chars) {
            numbers[c - '0'] += 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < numbers[i]; j++) {
                sb.append(i);
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
