package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LuckyStraight {

    @Test
    void case1() {
        int score = 123402;
        String answer = "LUCKY";
        assertThat(solution(score)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int score = 7755;
        String answer = "READY";
        assertThat(solution(score)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int score = 11;
        String answer = "LUCKY";
        assertThat(solution(score)).isEqualTo(answer);
    }

    private String solution(int score) {
        String letters = String.valueOf(score);
        int length = letters.length();
        int half = length / 2;
        int frontSum = 0;
        int backSum = 0;

        for (int i = 0; i < half; i++) {
            frontSum += letters.charAt(i) - '0';
            backSum += letters.charAt(i + half) - '0';
        }

        return frontSum == backSum ? "LUCKY" : "READY";
    }
}
